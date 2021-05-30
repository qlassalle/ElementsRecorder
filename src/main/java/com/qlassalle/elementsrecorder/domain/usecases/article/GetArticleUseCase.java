package com.qlassalle.elementsrecorder.domain.usecases.article;

import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetArticleUseCase {

    private final ArticleRepository articleRepository;

    public List<Article> findAll(UUID userId) {
        return articleRepository.findAll(userId);
    }
}
