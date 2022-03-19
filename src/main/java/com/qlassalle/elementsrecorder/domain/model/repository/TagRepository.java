package com.qlassalle.elementsrecorder.domain.model.repository;

import com.qlassalle.elementsrecorder.domain.model.Tag;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface TagRepository {
    Tag save(Tag tag);
    Set<Tag> findAll();
    Optional<Tag> findByNameAndUser(String name, UUID userId);
}
