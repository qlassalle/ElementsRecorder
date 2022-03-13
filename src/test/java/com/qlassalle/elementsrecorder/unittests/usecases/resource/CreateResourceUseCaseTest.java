package com.qlassalle.elementsrecorder.unittests.usecases.resource;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.input.ResourceCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.resource.CreateResourceUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;

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

    @DisplayName("User should be able to create an resource")
    @Test
    void shouldCreateResource() {
        String name = "Java for noobs";
        String description = "Gotta start somewhere";
        int rating = 5;
        String url = "www.noobs.com";
        User user = new User(uuidProvider.generate(), "email@gmail.com", "Azertyuiop0.");
        Resource expectedResource = buildExpectedResource(name, description, rating, url);

        Resource resource = createResourceUseCase.create(new ResourceCreationRequest(name, description, rating, url),
                                                         user.getId());

        assertThat(validator.validate(resource)).isEmpty();
        assertThat(resource).usingRecursiveComparison()
                            .ignoringFields("createdAt", "updatedAt")
                            .isEqualTo(expectedResource);
        assertThat(resource.getCreatedAt()).isNotNull();
        assertThat(resource.getUpdatedAt()).isNotNull();
    }

    @DisplayName("Resource should not be created with invalid URL")
    @Test
    void shouldNotCreateResourceWithInvalidUrl() {
        var resourceCreationRequest = new ResourceCreationRequest("Java 101", "Desc", 1, "nogoodurl.com");
        var validationResult = validator.validate(resourceCreationRequest);
        assertThat(validationResult).hasSize(1);
        assertThat(validationResult.iterator().next().getMessage()).isEqualTo("{resource.url.invalid}");
    }

    private Resource buildExpectedResource(String name, String description, int rating, String url) {
        return Resource.builder()
                       .id(uuidProvider.generate())
                       .name(name)
                       .description(description)
                       .rating(rating)
                       .url(url)
                       .userId(uuidProvider.generate())
                       .build();
    }

}
