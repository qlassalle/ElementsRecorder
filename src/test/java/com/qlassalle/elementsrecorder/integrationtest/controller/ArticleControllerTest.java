package com.qlassalle.elementsrecorder.integrationtest.controller;

import com.qlassalle.elementsrecorder.integrationtest.IntegrationTestBase;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static com.ekino.oss.jcv.assertion.hamcrest.JsonMatchers.jsonMatcher;
import static org.assertj.db.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest extends IntegrationTestBase {

    private Table table;
    private static final String BASE_URL = "/article";
    private static final String USERNAME = "testemail@gmail.com";

    @BeforeEach
    void setUp() {
        table = new Table(dataSource, "elements_recorder_schema.article");
    }

    @DisplayName("Should not create article with invalid URL")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldNotCreateArticleWithInvalidUrl() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "invalid_url.json")
               .statusCode(400)
               .body(jsonMatcher(outputFile("invalid_url.json")));
    }

    @DisplayName("Should create an article")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldCreateAnArticle() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "valid_article.json")
               .statusCode(201)
               .body(jsonMatcher(outputFile("valid_article.json")));
    }

    @DisplayName("Should get all articles for user")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldGetAllArticlesForUser() {
        givenAuthenticatedGetRequest(BASE_URL, USERNAME, 200, "get/all_articles.json");
    }

    @DisplayName("Should get one article by id for user")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldGetArticleForUser() {
        String articleId = "00000000-0000-0000-0000-000000000001";
        givenAuthenticatedGetRequest(BASE_URL + "/" + articleId, USERNAME, 200, "get/article_by_id.json");
    }

    @DisplayName("Should return 404 if user doesn't own article")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldReturnNotFoundIfUserDoesNotOwnArticle() {
        String articleId = "00000000-0000-0000-0000-000000000003";
        givenAuthenticatedGetRequest(BASE_URL + "/" + articleId, USERNAME, 404, "get/article_not_owned_by_user.json");
    }

    @DisplayName("Should delete article")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldDeleteArticle() {
        String articleId = "00000000-0000-0000-0000-000000000001";
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + articleId, USERNAME);

        assertThat(table).hasNumberOfRows(2);
    }

    @DisplayName("Should do nothing when article does not exist")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldDoNothingWhenArticleDoesNotExist() {
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + UUID.randomUUID(), USERNAME);

        assertThat(table).hasNumberOfRows(3);
    }

    @DisplayName("Should do nothing when user does not own article")
    @Test
    @Sql("articlecontrollertest/sql/create_user.sql")
    void shouldDoNothingIfUserDoesNotOwnArticle() {
        String articleId = "00000000-0000-0000-0000-000000000003";
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + articleId, USERNAME);

        assertThat(table).hasNumberOfRows(3);
    }
}
