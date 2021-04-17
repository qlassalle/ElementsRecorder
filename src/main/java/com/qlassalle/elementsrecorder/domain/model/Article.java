package com.qlassalle.elementsrecorder.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
public class Article {

    @Setter
    private Long id;

    private final String name;

    private final String description;

    private final short rating;

    private final String url;

    private final Instant createdAt;
    private final Instant updatedAt;
}
