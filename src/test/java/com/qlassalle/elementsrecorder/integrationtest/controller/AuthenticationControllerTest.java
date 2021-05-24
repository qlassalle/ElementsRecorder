package com.qlassalle.elementsrecorder.integrationtest.controller;

import com.qlassalle.elementsrecorder.integrationtest.IntegrationTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest extends IntegrationTestBase {

    private static final String BASE_URL = "/authenticate";
    private static final String REGISTER_URL = BASE_URL + "/register";

    @DisplayName("Should authenticate user with correct credentials")
    @Test
    @Sql("authenticationcontrollertest/sql/create_user.sql")
    void shouldAuthenticateUser() {
        String inputFile = inputFile("correct_credentials.json");
        postJson(BASE_URL + "/", inputFile).isOk()
                                           .expectBody()
                                           .jsonPath("access_token")
                                           .isNotEmpty();
    }

    @DisplayName("Should return bad credentials when wrong password")
    @Test
    @Sql("authenticationcontrollertest/sql/create_user.sql")
    void shouldReturnBadCredentialsWhenWrongPassword() {
        String inputFile = inputFile("wrong_credentials.json");
        postJson(BASE_URL + "/", inputFile).isForbidden()
                                           .expectBody()
                                           .json(getJsonAsString(outputFile("wrong_credentials.json")));
    }

    @DisplayName("Should allow new user to register")
    @Test
    void shouldAllowNewUserToRegister() {
        String inputFile = inputFile("register_correct_input.json");
        postJson(REGISTER_URL, inputFile).isOk()
                                         .expectBody()
                                         .jsonPath("access_token")
                                         .isNotEmpty();
    }

    @DisplayName("Should fail when password doesn't match policy")
    @Test
    void shouldFailWhenPasswordDoesntMatchPolicy() {
        String inputFile = inputFile("register_invalid_password.json");
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(outputFile("register_invalid_password.json")));
    }

    @DisplayName("Should fail when client is already registered")
    @Test
    @Sql("authenticationcontrollertest/sql/create_user.sql")
    void shouldFailWhenClientIsAlreadyRegistered() {
        String inputFile = inputFile("register_duplicate_email.json");
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(outputFile("email_already_used.json")));
    }

    @DisplayName("Should fail when passwords mismatch")
    @Test
    void shouldFailWhenPasswordsMismatch() {
        String inputFile = inputFile("register_mismatch_password.json");
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(outputFile("mismatch_password.json")));
    }

    @DisplayName("Should fail when user doesn't provide a correct email address")
    @Test
    void shouldFailWhenUserDoesntProvideCorrectEmail() {
        String inputFile = inputFile("register_incorrect_email.json");
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(outputFile("register_incorrect_email.json")));
    }
}