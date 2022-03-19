package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.usecases.tag.GetOrCreateTagUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final GetOrCreateTagUseCase getOrCreateTagUseCase;

    public Set<Tag> getOrCreate(Set<String> tags, UUID userId) {
        return Optional.ofNullable(tags)
                       .orElse(Collections.emptySet())
                       .stream()
                       .map(tag -> getOrCreateTagUseCase.getOrCreate(tag, userId))
                       .collect(Collectors.toSet());
    }
}
