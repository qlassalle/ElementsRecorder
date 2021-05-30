package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.adapters.entities.mappers.ArticleMapper;
import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaArticleRepository;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.input.ArticleCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.article.CreateArticleUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final JpaArticleRepository articleRepository;
    private final CreateArticleUseCase createArticleUseCase;
    private final ArticleMapper articleMapper = new ArticleMapper();

    public List<Article> findAll(UUID userId) {
        return articleRepository.findAllByUserId(userId)
                                .stream()
                                .map(articleMapper::map)
                                .collect(Collectors.toList());
    }

    public Article findById(Long id, UserEntity user) {
        ArticleEntity article = findByIdAndUser(id, user.getId());
        return articleMapper.map(article);
    }

    private ArticleEntity findByIdAndUser(Long id, UUID userId) {
        return articleRepository.findByIdAndUserId(id, userId)
                                .orElseThrow(() -> new NoSuchElementException("No such article with this id"));
    }

    public Article create(ArticleCreationRequest creationRequest, UUID userId) {
        Article article = createArticleUseCase.create(creationRequest, userId);
        log.info("Created article {}", article);
        return article;
    }

    public void delete(Long id, UserEntity user) {
        ArticleEntity article = findByIdAndUser(id, user.getId());
        articleRepository.delete(article);
        log.info("Deleted entity with id {}", id);
    }

//    public Article update(Long id, Article article, UserEntity user) {
//        findByIdAndUser(id, user);
//        article.setId(id);
//        ArticleEntity entity = articleRepository.save(ArticleMapper.map(article, user));
//        log.info("Updated {}", article.toString());
//        return ArticleMapper.map(entity);
//    }
}
