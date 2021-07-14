package com.qlassalle.elementsrecorder.integrationtest.controller;

import com.qlassalle.elementsrecorder.integrationtest.IntegrationTestBase;
import com.qlassalle.elementsrecorder.integrationtest.utils.HttpTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Stream;

import static com.ekino.oss.jcv.assertion.hamcrest.JsonMatchers.jsonMatcher;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest extends IntegrationTestBase {

    private static final String BASE_URL = "/authenticate";
    private static final String REGISTER_URL = BASE_URL + "/register";

    @DisplayName("Should authenticate user with correct credentials")
    @Test
    @Sql("authenticationcontrollertest/sql/create_user.sql")
    void shouldAuthenticateUser() {
        postUnauthenticatedJsonAndAssertStatusCodeAndBody(BASE_URL, "correct_credentials.json",200);
    }

    @DisplayName("Should return bad credentials when wrong password")
    @Test
    @Sql("authenticationcontrollertest/sql/create_user.sql")
    void shouldReturnBadCredentialsWhenWrongPassword() {
        postUnauthenticatedJsonAndAssertStatusCodeAndBody(BASE_URL, "wrong_credentials.json", 403);
    }

    @DisplayName("Should return bad credentials when user does not exist")
    @Test
    @Sql("authenticationcontrollertest/sql/create_user.sql")
    void shouldNotExist() {
        postUnauthenticatedJsonAndAssertStatusCodeAndBody(BASE_URL, "user_does_not_exist.json", 403);
    }

    @DisplayName("Should allow new user to register")
    @Test
    void shouldAllowNewUserToRegister() {
        buildRequest().body(inputFile("register_correct_input.json"))
                      .post(REGISTER_URL)
                      .then()
                      .statusCode(200)
                      .body(jsonMatcher(outputFile("correct_credentials.json")));
    }

    @ParameterizedTest
    @MethodSource("registerTestCases")
    @Sql("authenticationcontrollertest/sql/create_user.sql")
    public void testRegisterTestCases(HttpTestCase testCase) {
        postUnauthenticatedJsonAndAssertStatusCodeAndBody(testCase.getUrl(), testCase.getInputFilename(),
                                                          testCase.getStatusCode());
    }

    @SuppressWarnings("unused")
    public static Stream<Arguments> registerTestCases() {
        return Stream.of(
                Arguments.of(new HttpTestCase(REGISTER_URL, "register_invalid_password.json", 400, "Should fail when " +
                        "password doesn't match policy")),
                Arguments.of(new HttpTestCase(REGISTER_URL, "email_already_used.json", 409, "Should fail when user " +
                        "is already registered")),
                Arguments.of(new HttpTestCase(REGISTER_URL, "register_mismatch_password.json", 400, "Should fail when" +
                        " password mismatch")),
                Arguments.of(new HttpTestCase(REGISTER_URL, "register_incorrect_email.json", 400, "Should fail when " +
                        "user doesn't provide a correct email address"))
        );
    }
}
