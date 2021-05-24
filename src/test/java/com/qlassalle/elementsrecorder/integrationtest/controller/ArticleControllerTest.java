package com.qlassalle.elementsrecorder.integrationtest.controller;

import com.qlassalle.elementsrecorder.integrationtest.IntegrationTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.ekino.oss.jcv.assertion.hamcrest.JsonMatchers.jsonMatcher;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest extends IntegrationTestBase {

    private static final String BASE_URL = "/article";
    private static final String USERNAME = "testemail@gmail.com";

    @DisplayName("Should not create article with invalid URL")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldNotCreateArticleWithInvalidUrl() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "invalid_url.json")
               .statusCode(400)
               .body(jsonMatcher(getJsonAsString(outputFile("invalid_url.json"))));
    }

    @DisplayName("Should create an article")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldCreateAnArticle() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "valid_article.json")
               .statusCode(201)
               .body(jsonMatcher(getJsonAsString(outputFile("valid_article.json"))));
    }
}
