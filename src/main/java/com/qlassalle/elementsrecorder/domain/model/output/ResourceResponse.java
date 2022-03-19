package com.qlassalle.elementsrecorder.domain.model.output;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.Tag;
import lombok.Getter;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public final class ResourceResponse {
    private final UUID id;
    private final String name;
    private final String description;
    private final String url;
    private final int rating;
    private final List<TagResponse> tags;
    private final UUID userId;
    private final Instant createdAt;
    private final Instant updatedAt;


    public ResourceResponse(Resource resource, Set<Tag> tags) {
        this.id = resource.getId();
        this.name = resource.getName();
        this.description = resource.getDescription();
        this.url = resource.getUrl();
        this.rating = resource.getRating();
        this.tags = buildTags(tags);
        this.userId = resource.getUserId();
        this.createdAt = resource.getCreatedAt();
        this.updatedAt = resource.getUpdatedAt();
    }

    private List<TagResponse> buildTags(Set<Tag> tags) {
        return Optional.ofNullable(tags)
                       .orElse(Collections.emptySet())
                       .stream()
                       .map(tag -> new TagResponse(tag.id(), tag.name()))
                       .sorted(Comparator.comparing(tag -> tag.name))
                       .collect(Collectors.toList());
    }

    private record TagResponse(UUID id, String name) {
    }
}
