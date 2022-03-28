package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.usecases.tag.GetOrCreateTagUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.tag.GetTagUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final GetOrCreateTagUseCase getOrCreateTagUseCase;
    private final GetTagUseCase getTagUseCase;

    public Set<Tag> getOrCreate(Set<String> tags, UUID userId) {
        return Optional.ofNullable(tags)
                       .orElse(Collections.emptySet())
                       .stream()
                       .map(tag -> getOrCreateTagUseCase.getOrCreate(tag, userId))
                       .collect(Collectors.toSet());
    }

    public List<Tag> findAll(UUID userId) {
        return getTagUseCase.findAll(userId);
    }
}
