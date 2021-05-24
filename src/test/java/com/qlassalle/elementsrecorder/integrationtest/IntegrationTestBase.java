package com.qlassalle.elementsrecorder.integrationtest;

import com.qlassalle.elementsrecorder.infra.security.JwtUtil;
import com.qlassalle.elementsrecorder.integrationtest.exceptions.ReadingTestFileException;
import com.qlassalle.elementsrecorder.integrationtest.utils.ClassPathResources;
import com.qlassalle.elementsrecorder.integrationtest.utils.TruncateTables;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@TruncateTables
public abstract class IntegrationTestBase {

    @Autowired
    private WebTestClient client;

    @Autowired
    JwtUtil jwtUtil;

    @LocalServerPort
    protected int serverPort;

    protected static final String INPUT_BASE_PATH = "input/";
    protected static final String OUTPUT_BASE_PATH = "output/";

    protected String getJsonAsString(String path) {
        try {
            return new String(ClassPathResources.classResource(this, path).getInputStream().readAllBytes(),
                              StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new ReadingTestFileException("Unable to read file " + path);
        }
    }

    protected StatusAssertions postJson(String url, String inputFile) {
        return buildRequest(url, inputFile).exchange()
                                           .expectStatus();
    }

    protected ValidatableResponse givenAuthenticatedPostJsonRequest( String url, String username, String inputFile) {
        return given().config(RestAssuredConfig.config()
                                               .encoderConfig(EncoderConfig.encoderConfig()
                                                                           .encodeContentTypeAs("application/json", JSON)))
                      .port(serverPort)
                      .headers(Map.of("Content-Type", JSON, "Authorization",
                                      "Bearer " + jwtUtil.generateToken(username)))
                      .body(getJsonAsString(inputFile(inputFile)))
                      .post(url)
                      .then();
    }

    private WebTestClient.RequestHeadersSpec<?> buildRequest(String url, String inputFile) {
        return client.post()
                     .uri(url)
                     .header("Content-Type", "application/json")
                     .bodyValue(getJsonAsString(inputFile));
    }

    protected String outputFile(String filename) {
        return OUTPUT_BASE_PATH + filename;
    }

    protected String inputFile(String filename) {
        return INPUT_BASE_PATH + filename;
    }
}
