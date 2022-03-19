package com.qlassalle.elementsrecorder.unittests.usecases.resource;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.input.ResourceCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.resource.CreateResourceUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryResourceRepository;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateResourceUseCaseTest {

    private final UUIDProvider uuidProvider = new FixedUUIDProvider();
    private Validator validator;
    private CreateResourceUseCase createResourceUseCase;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.createResourceUseCase = new CreateResourceUseCase(new InMemoryResourceRepository(), uuidProvider);
    }

    @ParameterizedTest
    @MethodSource("createTestCases")
    void shouldCreateResource(ResourceTestCase testCase) {
        Resource expectedResource = buildExpectedResource(testCase.name, testCase.description, testCase.rating,
                                                          testCase.url, testCase.getTagsId());

        Resource resource = createResourceUseCase.create(new ResourceCreationRequest(testCase.name,
                                                                                     testCase.description,
                                                                                     testCase.rating, testCase.url),
                                                         testCase.getTagsId(), uuidProvider.generate());

        assertThat(validator.validate(resource)).isEmpty();
        assertThat(resource).usingRecursiveComparison()
                            .ignoringFields("createdAt", "updatedAt")
                            .isEqualTo(expectedResource);
        assertThat(resource.getCreatedAt()).isNotNull();
        assertThat(resource.getUpdatedAt()).isNotNull();
    }

    public static Stream<Arguments> createTestCases() {
        return Stream.of(
                Arguments.of(new ResourceTestCase("Java for noobs", "Gotta start somewhere", 5, "www.noobs.com",
                                                  Collections.emptySet(), "User should be able to create a resource")),
                Arguments.of(new ResourceTestCase("Quarkus", "Super fast Java apps", 5, "www.quarkus.io",
                                                  Set.of("Java", "microservices"),
                                                  "User should be able to create resource with new tags"))
        );
    }

    @DisplayName("Resource should not be created with invalid URL")
    @Test
    void shouldNotCreateResourceWithInvalidUrl() {
        var resourceCreationRequest = new ResourceCreationRequest("Java 101", "Desc", 1, "nogoodurl.com",
                                                                  Collections.emptySet());
        var validationResult = validator.validate(resourceCreationRequest);
        assertThat(validationResult).hasSize(1);
        assertThat(validationResult.iterator().next().getMessage()).isEqualTo("{resource.url.invalid}");
    }

    private Resource buildExpectedResource(String name, String description, int rating, String url, Set<UUID> tagsId) {
        return Resource.builder()
                       .id(uuidProvider.generate())
                       .name(name)
                       .description(description)
                       .rating(rating)
                       .url(url)
                       .tagsId(tagsId)
                       .userId(uuidProvider.generate())
                       .build();
    }

    @Getter
    private static final class ResourceTestCase {
        private final String name;
        private final String description;
        private final int rating;
        private final String url;
        private final Set<UUID> tagsId;
        private final String testCaseName;

        public ResourceTestCase(String name, String description, int rating, String url, Set<String> tags,
                                String testCaseName) {
            this.name = name;
            this.description = description;
            this.rating = rating;
            this.url = url;
            this.tagsId = tags.stream()
                                .map(tag -> UUID.randomUUID())
                                .collect(Collectors.toSet());
            this.testCaseName = testCaseName;
        }

        @Override
        public String toString() {
            return testCaseName;
        }
    }
}
