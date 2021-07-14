package com.qlassalle.elementsrecorder.integrationtest;

import com.qlassalle.elementsrecorder.infra.security.JwtUtil;
import com.qlassalle.elementsrecorder.integrationtest.exceptions.ReadingTestFileException;
import com.qlassalle.elementsrecorder.integrationtest.utils.ClassPathResources;
import com.qlassalle.elementsrecorder.integrationtest.utils.TruncateTables;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.ekino.oss.jcv.assertion.hamcrest.JsonMatchers.jsonMatcher;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@TruncateTables
public abstract class IntegrationTestBase {

    @Autowired
    JwtUtil jwtUtil;

    @LocalServerPort
    protected int serverPort;

    protected static final String INPUT_BASE_PATH = "input/";
    protected static final String OUTPUT_BASE_PATH = "output/";

    protected String getJsonAsString(String path) {
        try {
            return new String(ClassPathResources.classResource(this, path)
                                                .getInputStream()
                                                .readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new ReadingTestFileException("Unable to read file " + path);
        }
    }

    protected ValidatableResponse givenAuthenticatedPostJsonRequest(String url, String username, String inputFile) {
        return buildAuthenticatedRequest(username)
                      .body(inputFile(inputFile))
                      .post(url)
                      .then();
    }

    private RequestSpecification buildAuthenticatedRequest(String username) {
        return given().port(serverPort)
                      .headers(Map.of("Content-Type", JSON, "Authorization",
                                      "Bearer " + jwtUtil.generateToken(username)));
    }

    protected void postUnauthenticatedJsonAndAssertStatusCodeAndBody(String url, String filename, int statusCode) {
        buildRequest().body(inputFile(filename))
                      .post(url)
                      .then()
                      .statusCode(statusCode)
                      .body(jsonMatcher(outputFile(filename)));
    }

    protected void givenAuthenticatedGetRequest(String url, String username, int statusCode, String outputFile) {
        buildAuthenticatedRequest(username).get(url)
                                           .then()
                                           .statusCode(statusCode)
                                           .body(jsonMatcher(outputFile(outputFile)));
    }

    protected RequestSpecification buildRequest() {
        return given().port(serverPort)
                      .headers(Map.of("Content-Type", JSON));
    }

    protected String outputFile(String filename) {
        return getJsonAsString(OUTPUT_BASE_PATH + filename);
    }

    protected String inputFile(String filename) {
        return getJsonAsString(INPUT_BASE_PATH + filename);
    }
}
