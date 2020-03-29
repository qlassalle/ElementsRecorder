package com.qlassalle.elementsrecorder.model.mappers;

import com.qlassalle.elementsrecorder.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.model.Article;

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
                      .createdAt(articleEntity.getCreatedAt())
                      .updatedAt(articleEntity.getUpdatedAt())
                      .build();
    }
}
