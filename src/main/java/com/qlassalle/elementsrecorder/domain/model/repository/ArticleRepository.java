package com.qlassalle.elementsrecorder.domain.model.repository;

import com.qlassalle.elementsrecorder.domain.model.Article;

public interface ArticleRepository {
    Article save(Article article);
}
