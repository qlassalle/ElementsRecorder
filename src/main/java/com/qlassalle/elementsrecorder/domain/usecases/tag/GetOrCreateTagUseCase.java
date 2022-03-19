package com.qlassalle.elementsrecorder.domain.usecases.tag;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.repository.TagRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class GetOrCreateTagUseCase {

    private final TagRepository tagRepository;
    private final UUIDProvider uuidProvider;

    public Tag getOrCreate(String name, UUID userId) {
        name = name.trim();
        Optional<Tag> oTag = tagRepository.findByNameAndUser(name, userId);
        if (oTag.isPresent()) {
            return oTag.get();
        }

        var tag = new Tag(uuidProvider.generate(), name, userId);
        return tagRepository.save(tag);
    }
}
