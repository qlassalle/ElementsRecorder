package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.exceptions.ResourceNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InMemoryArticleRepository implements ArticleRepository {

    private final List<Article> articles = new ArrayList<>();

    @Override
    public Article save(Article article) {
        articles.add(article);
        return article;
    }

    @Override
    public List<Article> findAll(UUID userId) {
        return articles.stream()
                       .filter(article -> article.getUserId()
                                                 .equals(userId))
                       .collect(Collectors.toList());
    }

    @Override
    public Article findById(UUID articleId, UUID userId) {
        return articles.stream()
                .filter(article -> article.getId().equals(articleId) && article.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No article found with id %s", articleId));
    }

    @Override
    public void delete(UUID articleId, UUID userId) {
        articles.removeIf(article -> article.getId().equals(articleId) && article.getUserId().equals(userId));
    }
}
