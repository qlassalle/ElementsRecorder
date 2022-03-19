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
@Sql("shared/sql/create_user.sql")
public class ResourceControllerTest extends IntegrationTestBase {

    private Table tagResourceTable;
    private Table table;
    private Table tagTable;
    private static final String BASE_URL = "/resource";
    private static final String USERNAME = "testemail@gmail.com";

    @BeforeEach
    void setUp() {
        table = new Table(dataSource, "elements_recorder_schema.resource");
        tagResourceTable = new Table(dataSource, "elements_recorder_schema.tag_resource");
        tagTable = new Table(dataSource, "elements_recorder_schema.tag");
    }

    @DisplayName("Should not create resource with invalid URL")
    @Test
    void shouldNotCreateResourceWithInvalidUrl() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "create/invalid_url.json")
               .statusCode(400)
               .body(jsonMatcher(outputFile("create/invalid_url.json")));
    }

    @DisplayName("Should create a resource with existing tags only")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_tags.sql"})
    void shouldCreateAResourceWithExistingTagsOnly() {
        assertThat(tagTable).hasNumberOfRows(2);
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "create/valid_resource.json")
               .statusCode(201)
               .body(jsonMatcher(outputFile("create/valid_resource.json")));

        assertThat(tagTable).hasNumberOfRows(2);
        assertThat(tagResourceTable).hasNumberOfRows(2);
    }

    @DisplayName("Should create a resource with new tags")
    @Test
    void shouldCreateAResourceWithNewTags() {
        assertThat(new Table(dataSource, "elements_recorder_schema.tag")).hasNumberOfRows(0);
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "create/valid_resource_with_new_tags.json")
                .statusCode(201)
                .body(jsonMatcher(outputFile("create/valid_resource_with_new_tags.json")));

        assertThat(new Table(dataSource, "elements_recorder_schema.tag")).hasNumberOfRows(2);
        assertThat(tagResourceTable).hasNumberOfRows(2);
    }

    @DisplayName("Should create a resource with new and existing tags")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_tags.sql"})
    void shouldCreateAResourceWithNewAndExistingTags() {
        assertThat(new Table(dataSource, "elements_recorder_schema.tag")).hasNumberOfRows(2);
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "create/valid_resource_with_new_and_existing_tags.json")
                .statusCode(201)
                .body(jsonMatcher(outputFile("create/valid_resource_with_new_and_existing_tags.json")));

        assertThat(new Table(dataSource, "elements_recorder_schema.tag")).hasNumberOfRows(3);
        assertThat(tagResourceTable).hasNumberOfRows(3);
    }

    @DisplayName("Should create a resource without tags")
    @Test
    void shouldCreateAResourceWithoutTags() {
        givenAuthenticatedPostJsonRequest(BASE_URL, USERNAME, "create/valid_resource_no_tags.json")
                .statusCode(201)
                .body(jsonMatcher(outputFile("create/valid_resource_no_tags.json")));

        assertThat(tagResourceTable).hasNumberOfRows(0);
    }

    @DisplayName("Should get all resources for user")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_resources_with_and_without_tags.sql"})
    void shouldGetAllResourcesForUser() {
        givenAuthenticatedGetRequest(BASE_URL, USERNAME, 200, "get/all_resources.json");
    }

    @DisplayName("Should get one resource by id for user")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_resources_without_tag.sql"})
    void shouldGetResourceForUser() {
        String resourceId = "00000000-0000-0000-0000-000000000001";
        givenAuthenticatedGetRequest(BASE_URL + "/" + resourceId, USERNAME, 200, "get/resource_by_id.json");
    }

    @DisplayName("Should get one resource with tags by id for user")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_resources_with_tags.sql"})
    void shouldGetResourceWithTagsForUser() {
        String resourceId = "98b8fdc6-acf2-4e46-9419-150e42d078bd";
        givenAuthenticatedGetRequest(BASE_URL + "/" + resourceId, USERNAME, 200, "get/resource_with_tags_by_id.json");
    }

    @DisplayName("Should return 404 if user doesn't own resource")
    @Test
    void shouldReturnNotFoundIfUserDoesNotOwnResource() {
        String resourceId = "00000000-0000-0000-0000-000000000003";
        givenAuthenticatedGetRequest(BASE_URL + "/" + resourceId, USERNAME, 404, "get/resource_not_owned_by_user.json");
    }

    @DisplayName("Should delete resource")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_resources_without_tag.sql"})
    void shouldDeleteResourceWithoutTag() {
        String resourceId = "00000000-0000-0000-0000-000000000001";
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + resourceId, USERNAME);

        assertThat(table).hasNumberOfRows(2);
    }

    @DisplayName("Should delete resource with tags")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_resources_with_tags.sql"})
    void shouldDeleteResourceWithTags() {
        String resourceId = "98b8fdc6-acf2-4e46-9419-150e42d078bd";
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + resourceId, USERNAME);

        assertThat(table).hasNumberOfRows(0);
        assertThat(tagResourceTable).hasNumberOfRows(1);
    }

    @DisplayName("Should delete and do nothing when resource does not exist")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_resources_without_tag.sql"})
    void shouldDoNothingWhenResourceDoesNotExist() {
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + UUID.randomUUID(), USERNAME);

        assertThat(table).hasNumberOfRows(3);
    }

    @DisplayName("Should delete and do nothing when user does not own resource")
    @Test
    @Sql({"shared/sql/create_user.sql", "resourcecontrollertest/sql/create_resources_without_tag.sql"})
    void shouldDeleteNothingIfUserDoesNotOwnResource() {
        String resourceId = "00000000-0000-0000-0000-000000000003";
        givenAuthenticatedDeleteRequest(BASE_URL + "/" + resourceId, USERNAME);

        assertThat(table).hasNumberOfRows(3);
    }
}
