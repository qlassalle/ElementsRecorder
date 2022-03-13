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
public class ResourceControllerTest extends IntegrationTestBase {

    private Table table;
    private static final String BASE_URL = "/resource";
    private static final String USERNAME = "testemail@gmail.com";

    @BeforeEach
    void setUp() {
        table = new Table(dataSource, "elements_recorder_schema.resource");
    }

    @DisplayName("Should not create resource with invalid URL")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldNotCreateResourceWithInvalidUrl() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "invalid_url.json")
               .statusCode(400)
               .body(jsonMatcher(outputFile("invalid_url.json")));
    }

    @DisplayName("Should create an resource")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldCreateAnResource() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "valid_resource.json")
               .statusCode(201)
               .body(jsonMatcher(outputFile("valid_resource.json")));
    }

    @DisplayName("Should get all resources for user")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldGetAllResourcesForUser() {
        givenAuthenticatedGetRequest(BASE_URL, USERNAME, 200, "get/all_resources.json");
    }

    @DisplayName("Should get one resource by id for user")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldGetResourceForUser() {
        String resourceId = "00000000-0000-0000-0000-000000000001";
        givenAuthenticatedGetRequest(BASE_URL + "/" + resourceId, USERNAME, 200, "get/resource_by_id.json");
    }

    @DisplayName("Should return 404 if user doesn't own resource")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldReturnNotFoundIfUserDoesNotOwnResource() {
        String resourceId = "00000000-0000-0000-0000-000000000003";
        givenAuthenticatedGetRequest(BASE_URL + "/" + resourceId, USERNAME, 404, "get/resource_not_owned_by_user.json");
    }

    @DisplayName("Should delete resource")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldDeleteResource() {
        String resourceId = "00000000-0000-0000-0000-000000000001";
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + resourceId, USERNAME);

        assertThat(table).hasNumberOfRows(2);
    }

    @DisplayName("Should do nothing when resource does not exist")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldDoNothingWhenResourceDoesNotExist() {
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + UUID.randomUUID(), USERNAME);

        assertThat(table).hasNumberOfRows(3);
    }

    @DisplayName("Should do nothing when user does not own resource")
    @Test
    @Sql("resourcecontrollertest/sql/create_user.sql")
    void shouldDoNothingIfUserDoesNotOwnResource() {
        String resourceId = "00000000-0000-0000-0000-000000000003";
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + resourceId, USERNAME);

        assertThat(table).hasNumberOfRows(3);
    }
}
