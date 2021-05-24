package com.qlassalle.elementsrecorder.adapters.entities.mappers;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.domain.model.Article;

public class ArticleMapper {

    private final UserMapper userMapper = new UserMapper();

    public Article map(ArticleEntity articleEntity) {
        return Article.builder()
                      .id(articleEntity.getId())
                      .description(articleEntity.getDescription())
                      .name(articleEntity.getName())
                      .rating(articleEntity.getRating())
                      .url(articleEntity.getUrl())
                      .user(userMapper.map(articleEntity.getUser()))
                      .createdAt(articleEntity.getCreatedAt())
                      .updatedAt(articleEntity.getUpdatedAt())
                      .build();
    }

    public ArticleEntity map(Article article) {
        var builder = ArticleEntity.builder();
        if (article.getId() != null) {
            builder.id(article.getId());
        }
        return builder.name(article.getName())
                      .description(article.getDescription())
                      .rating(article.getRating())
                      .url(article.getUrl())
                      .user(userMapper.map(article.getUser()))
                      .createdAt(article.getCreatedAt())
                      .updatedAt(article.getUpdatedAt())
                      .build();
    }
}
