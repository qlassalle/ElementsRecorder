package com.qlassalle.elementsrecorder.unittests.usecases.tag;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.model.repository.TagRepository;
import com.qlassalle.elementsrecorder.domain.usecases.tag.GetOrCreateTagUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryTagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GetOrCreateTagUseCaseTest {

    private GetOrCreateTagUseCase getOrCreateTagUseCase;
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
        tagRepository = new InMemoryTagRepository();
        getOrCreateTagUseCase = new GetOrCreateTagUseCase(tagRepository, new FixedUUIDProvider());
    }

    @DisplayName("Should save tag when it doesn't exist")
    @Test
    void shouldSaveTagWhenItDoesntExist() {
        var expectedTag = new Tag(DEFAULT_UUID, "Java", DEFAULT_UUID);
        var createdTag = getOrCreateTagUseCase.getOrCreate(" Java ", DEFAULT_UUID);

        assertThat(createdTag).usingRecursiveComparison()
                              .ignoringFields("createdAt", "updatedAt")
                              .isEqualTo(expectedTag);
        assertThat(tagRepository.findAll()).hasSize(1);
    }

    @DisplayName("Should not save tag if it already exists")
    @Test
    void shouldNotSaveTagIfItAlreadyExists() {
        var savedTag = new Tag(UUID.randomUUID(), "Angular", DEFAULT_UUID);
        tagRepository.save(savedTag);

        var foundTag = getOrCreateTagUseCase.getOrCreate("Angular", DEFAULT_UUID);

        assertThat(foundTag).isEqualTo(savedTag);
        assertThat(tagRepository.findAll()).hasSize(1);
    }

    @DisplayName("Should save tag if it already exists but for another user")
    @Test
    void shouldSaveTagIfItAlreadyExistsButForAnotherUser() {
        var savedTag = new Tag(UUID.randomUUID(), "Angular", UUID.randomUUID());
        tagRepository.save(savedTag);

        getOrCreateTagUseCase.getOrCreate("Angular", DEFAULT_UUID);
        assertThat(tagRepository.findAll()).hasSize(2);
    }
}
