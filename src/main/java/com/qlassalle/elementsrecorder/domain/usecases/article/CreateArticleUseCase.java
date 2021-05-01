package com.qlassalle.elementsrecorder.domain.usecases.article;

import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.input.ArticleCreationRequest;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateArticleUseCase {

    private final ArticleRepository articleRepository;
    private final UUIDProvider uuidProvider;

    public Article create(ArticleCreationRequest creationRequest, User user) {
        Article article = new Article(uuidProvider.generate(), creationRequest.name(), creationRequest.description(),
                                      creationRequest.rating(), creationRequest.url(), user);
        return articleRepository.save(article);
    }
}
