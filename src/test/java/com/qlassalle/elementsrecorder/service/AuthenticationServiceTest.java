package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.entities.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationServiceTest {

    private final UserRepository repository = Mockito.mock(UserRepository.class);

    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    private AuthenticationService service;

    @BeforeEach
    void setUp() {
        service = new AuthenticationService(repository, passwordEncoder);
    }

    @ParameterizedTest
    @MethodSource("passwordTestCases")
    void shouldVerifyPassword(PasswordTestCase testCase) {
        assertEquals(testCase.expectedResult, service.validatePassword(testCase.password));
    }

    private static Stream<Arguments> passwordTestCases() {
        return Stream.of(Arguments.of(new PasswordTestCase("azertyuiop", false, "Only lowercase should fail")),
                         Arguments.of(new PasswordTestCase("Azertyuiop", false, "Missing digit and special")),
                         Arguments.of(new PasswordTestCase("Azerty0uiop", false, "Missing special")),
                         Arguments.of(new PasswordTestCase("A0_p", false, "Password too short")),
                         Arguments.of(new PasswordTestCase("Pass 0_pass", false, "Whitespace should fail")),
                         Arguments.of(new PasswordTestCase("C0rrect_", true, "Correct password")),
                         Arguments.of(new PasswordTestCase("SentenceIs0K.", true, "Correct password")));
    }

    @AllArgsConstructor
    private static class PasswordTestCase {
        private final String password;
        private final boolean expectedResult;
        private final String testName;

        @Override
        public String toString() {
            return testName;
        }
    }
}