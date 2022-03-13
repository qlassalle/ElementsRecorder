package com.qlassalle.elementsrecorder.unittests.usecases.resource;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;
import com.qlassalle.elementsrecorder.domain.usecases.resource.DeleteResourceUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryResourceRepository;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteResourceUseCaseTest {

    private final UUIDProvider fixedUUIDProvider = new FixedUUIDProvider();
    private DeleteResourceUseCase deleteResourceUseCase;
    private ResourceRepository resourceRepository;

    @BeforeEach
    void setUp() {
        resourceRepository = new InMemoryResourceRepository();
        deleteResourceUseCase = new DeleteResourceUseCase(resourceRepository);
    }

    @DisplayName("Should delete existing resource")
    @Test
    void shouldDeleteExistingResource() {
        UUID resourceId = fixedUUIDProvider.generate();
        UUID userId = fixedUUIDProvider.generate();
        saveResource(resourceId, userId);

        deleteResourceUseCase.delete(resourceId, userId);

        assertThat(resourceRepository.findAll(userId)).isEqualTo(List.of());
    }

    @DisplayName("Should do nothing if resource does not exist")
    @Test
    void shouldDoNothingIfResourceDoesNotExist() {
        UUID userId = fixedUUIDProvider.generate();
        saveResource(fixedUUIDProvider.generate(), userId);

        deleteResourceUseCase.delete(UUID.randomUUID(), userId);

        assertThat(resourceRepository.findAll(userId).size()).isEqualTo(1);
    }

    @DisplayName("Should do nothing if user doesn't own resource")
    @Test
    void shouldDoNothingIfUserDoesNotOwnResource() {
        UUID resourceId = fixedUUIDProvider.generate();
        UUID userOwnerId = fixedUUIDProvider.generate();
        saveResource(resourceId, userOwnerId);

        deleteResourceUseCase.delete(resourceId, UUID.randomUUID());

        assertThat(resourceRepository.findAll(userOwnerId).size()).isEqualTo(1);
    }

    private void saveResource(UUID resourceId, UUID userId) {
        Resource existingResource = Resource.builder()
                                            .id(resourceId)
                                            .userId(userId)
                                            .build();

        resourceRepository.save(existingResource);
    }
}
