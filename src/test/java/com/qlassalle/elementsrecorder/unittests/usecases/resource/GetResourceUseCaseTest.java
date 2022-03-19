package com.qlassalle.elementsrecorder.unittests.usecases.resource;

import com.qlassalle.elementsrecorder.domain.exceptions.ResourceNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;
import com.qlassalle.elementsrecorder.domain.usecases.resource.GetResourceUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider.DEFAULT_UUID;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GetResourceUseCaseTest {

    private ResourceRepository resourceRepository;
    private GetResourceUseCase getResourceUseCase;
    private final UUID userId = UUID.fromString("c7a74e7b-1b1e-4075-86b5-1d578d0dce6a");

    @BeforeEach
    void setUp() {
        resourceRepository = new InMemoryResourceRepository();
        getResourceUseCase = new GetResourceUseCase(resourceRepository);
    }

    @DisplayName("Should return empty list when user has no resources")
    @Test
    void shouldReturnEmptyListWhenUserHasNoResources() {
        assertThat(getResourceUseCase.findAll(UUID.randomUUID())).isEqualTo(Collections.emptyList());
    }

    @DisplayName("Should return all resources for user")
    @Test
    void shouldReturnAllResourcesForUser() {
        Resource resource = generateResource();
        Resource resource2 = new Resource(UUID.randomUUID(), "Hello World", "Hello", 5, "somewhere.com",
                                          Set.of(UUID.randomUUID(), UUID.randomUUID()), userId);

        resourceRepository.save(resource);
        resourceRepository.save(resource2);

        List<Resource> savedResources = getResourceUseCase.findAll(userId);

        assertThat(savedResources).isEqualTo(List.of(resource, resource2));
    }

    @DisplayName("Should return only resources belonging to user")
    @Test
    void shouldReturnOnlyResourcesBelongingToUser() {
        Resource resource = generateResource();
        Resource resource2 = new Resource(UUID.randomUUID(), "Hello World", "Hello", 5, "somewhere.com", emptySet(),
                                          userId);

        resourceRepository.save(resource);
        resourceRepository.save(resource2);

        List<Resource> savedResources = getResourceUseCase.findAll(UUID.fromString("38032c19-1ce0-4f16-acb1-d8a8de08a84d"));

        assertThat(savedResources).isEqualTo(Collections.emptyList());
    }

    @DisplayName("Should return resource by id for user")
    @Test
    void shouldReturnResourceByIdForUser() {
        Resource resource = generateResource(DEFAULT_UUID);
        resourceRepository.save(resource);

        Resource retrievedResource = getResourceUseCase.findById(DEFAULT_UUID, userId);

        assertThat(retrievedResource).isEqualTo(resource);
    }

    @DisplayName("Should throw if resource is not found")
    @Test
    void shouldThrowIfResourceIsNotFound() {
        Resource resource = generateResource();
        resourceRepository.save(resource);
        UUID resourceId = UUID.randomUUID();

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> getResourceUseCase.findById(resourceId, userId))
                                                                  .withMessage("No resource found with id " + resourceId);
    }

    @DisplayName("Should throw if resource doesn't belong to user")
    @Test
    void shouldThrowIfResourceDoesntBelongToUser() {
        UUID resourceId = UUID.randomUUID();
        UUID otherUserId = UUID.randomUUID();
        Resource resource = generateResource(resourceId);
        resourceRepository.save(resource);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> getResourceUseCase.findById(resourceId, otherUserId))
                                                                  .withMessage("No resource found with id " + resourceId);
    }

    private Resource generateResource() {
        return new Resource(UUID.randomUUID(), "Hello World", "Hello", 5, "hello.com", emptySet(), userId);
    }

    private Resource generateResource(UUID uuid) {
        return new Resource(uuid, "Hello World", "Hello", 5, "hello.com", emptySet(), userId);
    }
}
