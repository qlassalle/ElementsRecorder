package com.qlassalle.elementsrecorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class Article {
    private final UUID id;
    private final String name;
    private final String description;
    private final int rating;
    private final String url;
    private final UUID userId;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Article(UUID id, String name, String description, int rating, String url, UUID userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.url = url;
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
        Article article = (Article) o;
        return rating == article.rating && id.equals(article.id) && name.equals(article.name) && description.equals(article.description) && url.equals(article.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, rating, url);
    }
}
