package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.input.ArticleCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.article.CreateArticleUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.article.DeleteArticleUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.article.GetArticleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final CreateArticleUseCase createArticleUseCase;
    private final GetArticleUseCase getArticleUseCase;
    private final DeleteArticleUseCase deleteArticleUseCase;

    public List<Article> findAll(UUID userId) {
        return getArticleUseCase.findAll(userId);
    }

    public Article findById(UUID id, UserEntity user) {
        return getArticleUseCase.findById(id, user.getId());
    }

    public Article create(ArticleCreationRequest creationRequest, UUID userId) {
        return createArticleUseCase.create(creationRequest, userId);
    }

    @Transactional
    public void delete(UUID id, UserEntity user) {
        deleteArticleUseCase.delete(id, user.getId());
    }

//    public Article update(Long id, Article article, UserEntity user) {
//        findByIdAndUser(id, user);
//        article.setId(id);
//        ArticleEntity entity = articleRepository.save(ArticleMapper.map(article, user));
//        log.info("Updated {}", article.toString());
//        return ArticleMapper.map(entity);
//    }
}
