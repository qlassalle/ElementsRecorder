package com.qlassalle.elementsrecorder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Builder
@Getter
@ToString
public class Article {

    private Long id;

    private String name;

    private String description;

    private short rating;

    private Instant createdAt;
    private Instant updatedAt;
}
