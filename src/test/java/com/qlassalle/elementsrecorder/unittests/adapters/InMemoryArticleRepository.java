package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryArticleRepository implements ArticleRepository {

    private final List<Article> articles = new ArrayList<>();

    @Override
    public Article save(Article article) {
        articles.add(article);
        return article;
    }
}
