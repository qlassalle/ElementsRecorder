package com.qlassalle.elementsrecorder.domain.usecases.tag;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.model.repository.TagRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetTagUseCase {

    private final TagRepository tagRepository;

    public List<Tag> findAll(UUID userId) {
        return tagRepository.findAll(userId);
    }
}
