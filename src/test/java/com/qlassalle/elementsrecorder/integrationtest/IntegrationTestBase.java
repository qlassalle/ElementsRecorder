package com.qlassalle.elementsrecorder.integrationtest;

import com.ekino.oss.jcv.assertion.hamcrest.JsonMatcherBuilder;
import com.qlassalle.elementsrecorder.infra.security.JwtUtil;
import com.qlassalle.elementsrecorder.integrationtest.exceptions.ReadingTestFileException;
import com.qlassalle.elementsrecorder.integrationtest.utils.ClassPathResources;
import com.qlassalle.elementsrecorder.integrationtest.utils.TruncateTables;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.postgresql.ds.PGSimpleDataSource;
import org.skyscreamer.jsonassert.JSONCompareMode;
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

    protected static PGSimpleDataSource dataSource = new PGSimpleDataSource();

    @LocalServerPort
    protected int serverPort;

    protected static final String INPUT_BASE_PATH = "input/";
    protected static final String OUTPUT_BASE_PATH = "output/";

    @BeforeAll
    static void beforeAll() {
        dataSource.setUrl("jdbc:postgresql://localhost:54320/dev_elements_recorder");
        dataSource.setUser("dev_elements_recorder_app");
        dataSource.setPassword("root");
    }

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

    protected void givenAuthenticatedGetRequest(String url, String username, int statusCode, String response) {
        buildAuthenticatedRequest(username).get(url)
                                           .then()
                                           .statusCode(statusCode)
                                           .body(JsonMatcherBuilder.create().mode(JSONCompareMode.STRICT).build(outputFile(response)));
    }

    protected void givenAuthenticatedGetRequestWithBody(String url, String username, int statusCode, String body) {
        buildAuthenticatedRequest(username).get(url)
                                           .then()
                                           .statusCode(statusCode)
                                           .body(JsonMatcherBuilder.create().mode(JSONCompareMode.STRICT).build(body));
    }

    protected void givenUnauthenticatedGetRequest(String url, int statusCode, String response) {
        buildRequest().get(url)
                      .then()
                      .statusCode(statusCode)
                      .body(JsonMatcherBuilder.create().mode(JSONCompareMode.STRICT).build(outputFile(response)));
    }

    protected void givenAuthenticatedDeleteRequest(String url, String username) {
        buildAuthenticatedRequest(username).delete(url)
                                           .then()
                                           .statusCode(204);
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
