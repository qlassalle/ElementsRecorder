package com.qlassalle.elementsrecorder.integrationtest.controller;

import com.qlassalle.elementsrecorder.integrationtest.IntegrationTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TagControllerTest extends IntegrationTestBase {

    private static final String URL = "/tags";
    private static final String USERNAME = "testemail@gmail.com";

    @DisplayName("Should return all tags for a user")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_tags.sql"})
    void shouldReturnAllTagsForAUser() {
        givenAuthenticatedGetRequest(URL, USERNAME, 200, "get/all_tags.json");
    }

    @DisplayName("Should get only tags belonging to user")
    @Test
    @Sql({"shared/sql/create_user.sql", "tagcontrollertest/sql/create_tags.sql"})
    void shouldGetOnlyTagsBelongingToUser() {
        givenAuthenticatedGetRequest(URL, USERNAME, 200, "get/all_tags.json");
    }

    @DisplayName("Should return empty list if user has no tags")
    @Test
    @Sql("shared/sql/create_user.sql")
    void shouldReturnEmptyListIfUserHasNoTag() {
        givenAuthenticatedGetRequestWithBody(URL, USERNAME, 200, "[]");
    }

    @DisplayName("Should result in 403 if user is unauthenticated")
    @Test
    void shouldResultIn403IfUserIsUnauthenticated() {
        givenUnauthenticatedGetRequest(URL, 403, "get/forbidden.json");
    }
}
