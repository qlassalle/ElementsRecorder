package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.model.repository.TagRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTagRepository implements TagRepository {

    private final List<Tag> tags = new ArrayList<>();

    @Override
    public Tag save(Tag tag) {
        tags.add(tag);
        return tag;
    }

    @Override
    public List<Tag> findAll(UUID userId) {
        return tags.stream()
                   .filter(tag -> tag.userId().equals(userId))
                   .collect(Collectors.toList());
    }

    @Override
    public Optional<Tag> findByNameAndUser(String name, UUID userId) {
        return tags.stream().filter(tag -> tag.name().equals(name) && tag.userId().equals(userId)).findFirst();
    }

    public int getTagsSize() {
        return tags.size();
    }
}
