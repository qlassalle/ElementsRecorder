package com.qlassalle.elementsrecorder.controller;

import com.qlassalle.elementsrecorder.IntegrationTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest extends IntegrationTestBase {

    private static final String BASE_URL = "/authenticate";
    private static final String REGISTER_URL = BASE_URL + "/register";
    private static final String INPUT_BASE_PATH = "input/authenticate/";
    private static final String OUTPUT_BASE_PATH = "output/authenticate/";

    @DisplayName("Should authenticate user with correct credentials")
    @Test
    public void shouldAuthenticateUser() {
        String inputFile = INPUT_BASE_PATH + "correct_credentials.json";
        postJson(BASE_URL + "/", inputFile).isOk()
                                           .expectBody()
                                           .jsonPath("access_token")
                                           .isNotEmpty();
    }

    @DisplayName("Should return bad credentials when wrong password")
    @Test
    public void shouldReturnBadCredentialsWhenWrongPassword() {
        String inputFile = INPUT_BASE_PATH + "wrong_credentials.json";
        postJson(BASE_URL + "/", inputFile).isForbidden()
                                           .expectBody()
                                           .json(getJsonAsString(OUTPUT_BASE_PATH + "wrong_credentials.json"));
    }

    @DisplayName("Should allow new user to register")
    @Test
    public void shouldAllowNewUserToRegister() {
        String inputFile = INPUT_BASE_PATH + "register_correct_input.json";
        postJson(REGISTER_URL, inputFile).isOk()
                                         .expectBody()
                                         .jsonPath("access_token")
                                         .isNotEmpty();
    }

    @DisplayName("Should fail when password doesn't match policy")
    @Test
    public void shouldFailWhenPasswordDoesntMatchPolicy() {
        String inputFile = INPUT_BASE_PATH + "register_invalid_password.json";
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(OUTPUT_BASE_PATH + "register_invalid_password.json"));
    }

    @DisplayName("Should fail when client is already registered")
    @Test
    public void shouldFailWhenClientIsAlreadyRegistered() {
        String inputFile = INPUT_BASE_PATH + "register_duplicate_email.json";
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(OUTPUT_BASE_PATH + "email_already_used.json"));
    }

    @DisplayName("Should fail when passwords mismatch")
    @Test
    public void shouldFailWhenPasswordsMismatch() {
        String inputFile = INPUT_BASE_PATH + "register_mismatch_password.json";
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(OUTPUT_BASE_PATH + "mismatch_password.json"));
    }

    @DisplayName("Should fail when user doesn't provide a correct email address")
    @Test
    public void shouldFailWhenUserDoesntProvideCorrectEmail() {
        String inputFile = INPUT_BASE_PATH + "register_incorrect_email.json";
        postJson(REGISTER_URL, inputFile).is4xxClientError()
                                         .expectBody()
                                         .json(getJsonAsString(OUTPUT_BASE_PATH + "register_incorrect_email.json"));
    }
}