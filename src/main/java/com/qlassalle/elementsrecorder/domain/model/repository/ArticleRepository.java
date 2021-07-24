package com.qlassalle.elementsrecorder.domain.model.repository;

import com.qlassalle.elementsrecorder.domain.model.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository {
    Article save(Article article);

    List<Article> findAll(UUID userId);

    Article findById(UUID articleId, UUID userId);

    void delete(UUID articleId, UUID userId);
}
