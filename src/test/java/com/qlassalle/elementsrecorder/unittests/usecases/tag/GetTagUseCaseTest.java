package com.qlassalle.elementsrecorder.unittests.usecases.tag;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.usecases.tag.GetTagUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryTagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class GetTagUseCaseTest {

    private final UUID userId = UUID.fromString("c7a74e7b-1b1e-4075-86b5-1d578d0dce6a");
    private InMemoryTagRepository inMemoryTagRepository = new InMemoryTagRepository();
    private GetTagUseCase getTagUseCase;

    @BeforeEach
    void setUp() {
        inMemoryTagRepository = new InMemoryTagRepository();
        getTagUseCase = new GetTagUseCase(inMemoryTagRepository);
    }

    @DisplayName("Should retrieve all tags for user")
    @Test
    void shouldRetrieveAllTagsForUser() {
        Tag tag = new Tag(UUID.fromString("00000000-0000-0000-0000-000000000001"), "Java", userId);
        inMemoryTagRepository.save(tag);
        var tags = getTagUseCase.findAll(userId);
        assertThat(List.of(tag)).usingRecursiveComparison()
                                .ignoringFields("createdAt", "updatedAt")
                                .isEqualTo(tags);
    }

    @DisplayName("Should return empty list if user has no tag")
    @Test
    void shouldReturnEmptyListIfUserHasNoTag() {
        var tags = getTagUseCase.findAll(userId);
        assertThat(tags).isEmpty();
    }
}
