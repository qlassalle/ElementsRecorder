package com.qlassalle.elementsrecorder.domain.usecases.article;

import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteArticleUseCase {

    private final ArticleRepository articleRepository;

    public void delete(UUID articleId, UUID userId) {
        articleRepository.delete(articleId, userId);
    }
}
