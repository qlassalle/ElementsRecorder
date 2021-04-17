package com.qlassalle.elementsrecorder.adapters.entities.mappers;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.domain.model.Article;

public class ArticleMapper {

    private ArticleMapper() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static Article map(ArticleEntity articleEntity) {
        return Article.builder()
                      .id(articleEntity.getId())
                      .description(articleEntity.getDescription())
                      .name(articleEntity.getName())
                      .rating(articleEntity.getRating())
                      .url(articleEntity.getUrl())
                      .createdAt(articleEntity.getCreatedAt())
                      .updatedAt(articleEntity.getUpdatedAt())
                      .build();
    }

    public static ArticleEntity map(Article article, UserEntity user) {
        var builder = ArticleEntity.builder();
        if (article.getId() != null) {
            builder.id(article.getId());
        }
        return builder.name(article.getName())
                      .description(article.getDescription())
                      .rating(article.getRating())
                      .url(article.getUrl())
                      .user(user)
                      .build();
    }
}
