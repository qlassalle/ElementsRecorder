package com.qlassalle.elementsrecorder.endpoints.models;

import com.qlassalle.elementsrecorder.domain.model.Article;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class ArticleDTO {

    UUID id;
    String name;
    String description;
    int rating;
    String url;
    UUID userId;
    Instant createdAt;
    Instant updatedAt;

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.name = article.getName();
        this.description = article.getDescription();
        this.rating = article.getRating();
        this.url = article.getUrl();
        this.userId = article.getUser().getId();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
