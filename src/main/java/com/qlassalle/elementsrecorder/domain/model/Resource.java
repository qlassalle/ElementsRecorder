package com.qlassalle.elementsrecorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@ToString
public class Resource {
    private final UUID id;
    private final String name;
    private final String description;
    private final int rating;
    private final String url;
    private final Set<UUID> tagsId;
    private final UUID userId;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Resource(UUID id, String name, String description, int rating, String url, Set<UUID> tagsId, UUID userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.url = url;
        this.tagsId = tagsId;
        this.userId = userId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resource resource = (Resource) o;
        return rating == resource.rating && id.equals(resource.id) && name.equals(resource.name) && description.equals(resource.description) && url.equals(resource.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, rating, url);
    }
}
