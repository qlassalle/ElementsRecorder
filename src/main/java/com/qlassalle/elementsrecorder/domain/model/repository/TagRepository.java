package com.qlassalle.elementsrecorder.domain.model.repository;

import com.qlassalle.elementsrecorder.domain.model.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository {
    Tag save(Tag tag);
    List<Tag> findAll(UUID userId);
    Optional<Tag> findByNameAndUser(String name, UUID userId);
}
