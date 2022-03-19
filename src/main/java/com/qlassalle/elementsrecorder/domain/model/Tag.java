package com.qlassalle.elementsrecorder.domain.model;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

public record Tag(UUID id, String name, UUID userId, Instant createdAt, Instant updatedAt) {
    // Constructor + annotation here for IntelliJ autocompletion
    @Builder
    public Tag {}

    public Tag(UUID id, String name, UUID userId) {
        this(id, name, userId, Instant.now(), Instant.now());
    }
}
