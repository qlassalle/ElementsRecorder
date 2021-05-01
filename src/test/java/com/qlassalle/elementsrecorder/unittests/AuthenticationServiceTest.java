package com.qlassalle.elementsrecorder.unittests;

import com.qlassalle.elementsrecorder.domain.exceptions.InvalidPasswordException;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.repository.UserRepository;
import com.qlassalle.elementsrecorder.service.AuthenticationService;
import com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider;
import com.qlassalle.elementsrecorder.unittests.adapters.UserInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AuthenticationServiceTest {

    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private final UUIDProvider uuidProvider = new FixedUUIDProvider();
    private UserRepository repository;

    private AuthenticationService service;

    @BeforeEach
    void setUp() {
        repository = new UserInMemoryRepository();
        service = new AuthenticationService(repository, passwordEncoder, uuidProvider);
    }

    @DisplayName("User with correct information should be able to sign up")
    @Test
    void shouldAllowUserWithCorrectDataToRegister() {
        String email = "mytestemail@gmail.com";
        String password = "MyStrongPassword0.";
        assertThat(repository.findByEmail(email)).isEmpty();
        User user = service.register(email, password);
        assertThat(repository.getByEmail(email)).isEqualTo(user);
    }

    @ParameterizedTest
    @MethodSource("passwordIncorrectTestCases")
    void shouldVerifyIncorrectPassword(PasswordTestCase testCase) {
        assertThatExceptionOfType(InvalidPasswordException.class)
                .isThrownBy(() -> service.register("hello@gmail.com", testCase.password))
                .withMessage("Your password must contain at least 8 characters, 1 uppercase " +
                                    "character, 1 lowercase character, 1 digit, 1 special " +
                                    "character and should not contain any whitespace");
    }

    @ParameterizedTest
    @MethodSource("passwordCorrectTestCases")
    void shouldVerifyCorrectPassword(PasswordTestCase testCase) {
        User user = service.register("hello@gmail.com", testCase.password);
        assertThat(user).isNotNull();
    }

    private static Stream<Arguments> passwordIncorrectTestCases() {
        return Stream.of(Arguments.of(new PasswordTestCase("azertyuiop", "Only lowercase should fail")),
                         Arguments.of(new PasswordTestCase("Azertyuiop", "Missing digit and special")),
                         Arguments.of(new PasswordTestCase("Azerty0uiop", "Missing special")),
                         Arguments.of(new PasswordTestCase("A0_p", "Password too short")),
                         Arguments.of(new PasswordTestCase("Pass 0_pass", "Whitespace should fail")));
    }

    private static Stream<Arguments> passwordCorrectTestCases() {
        return Stream.of(Arguments.of(new PasswordTestCase("C0rrect_", "Correct password")),
                         Arguments.of(new PasswordTestCase("SentenceIs0K.", "Correct password")),
                         Arguments.of(new PasswordTestCase("AL0ngPassword...FEIuéçDZ8Y42.", "Correct password")),
                         Arguments.of(new PasswordTestCase("AAAAAAAAAAa1.", "Correct password")),
                         Arguments.of(new PasswordTestCase("azerpMLKJSQ1290?./", "Correct password")));
    }

    private record PasswordTestCase(String password, String testName) {
        public String toString() {
            return testName;
        }
    }
}