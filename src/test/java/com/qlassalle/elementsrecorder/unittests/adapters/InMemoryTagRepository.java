package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.model.repository.TagRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class InMemoryTagRepository implements TagRepository {

    private final Set<Tag> tags = new HashSet<>();

    @Override
    public Tag save(Tag tag) {
        tags.add(tag);
        return tag;
    }

    @Override
    public Set<Tag> findAll() {
        return tags;
    }

    @Override
    public Optional<Tag> findByNameAndUser(String name, UUID userId) {
        return tags.stream().filter(tag -> tag.name().equals(name) && tag.userId().equals(userId)).findFirst();
    }
}
