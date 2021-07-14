package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.input.ArticleCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.article.CreateArticleUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.article.GetArticleUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final CreateArticleUseCase createArticleUseCase;
    private final GetArticleUseCase getArticleUseCase;

    public List<Article> findAll(UUID userId) {
        return getArticleUseCase.findAll(userId);
    }

    public Article findById(UUID id, UserEntity user) {
        return getArticleUseCase.findById(id, user.getId());
    }

    public Article create(ArticleCreationRequest creationRequest, UUID userId) {
        Article article = createArticleUseCase.create(creationRequest, userId);
        log.info("Created article {}", article);
        return article;
    }

    public void delete(Long id, UserEntity user) {
//        ArticleEntity article = findByIdAndUser(id, user.getId());
//        articleRepository.delete(article);
//        log.info("Deleted entity with id {}", id);
    }

//    public Article update(Long id, Article article, UserEntity user) {
//        findByIdAndUser(id, user);
//        article.setId(id);
//        ArticleEntity entity = articleRepository.save(ArticleMapper.map(article, user));
//        log.info("Updated {}", article.toString());
//        return ArticleMapper.map(entity);
//    }
}
