package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.adapters.entities.mappers.ArticleMapper;
import com.qlassalle.elementsrecorder.adapters.entities.mappers.UserMapper;
import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaArticleRepository;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.input.ArticleCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.article.CreateArticleUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final JpaArticleRepository articleRepository;
    private final CreateArticleUseCase createArticleUseCase;
    private final ArticleMapper articleMapper = new ArticleMapper();
    // TODO: 19/04/2021 remove this
    private final UserMapper userMapper = new UserMapper();

    public List<Article> findAll(UserEntity user) {
        return articleRepository.findAllByUser(user)
                                .stream()
                                .map(articleMapper::map)
                                .collect(Collectors.toList());
    }

    public Article findById(Long id, UserEntity user) {
        ArticleEntity article = findByIdAndUser(id, user);
        return articleMapper.map(article);
    }

    private ArticleEntity findByIdAndUser(Long id, UserEntity user) {
        return articleRepository.findByIdAndUser(id, user)
                                .orElseThrow(() -> new NoSuchElementException("No such article with this id"));
    }

    public Article create(ArticleCreationRequest creationRequest, UserEntity user) {
        Article article = createArticleUseCase.create(creationRequest, userMapper.map(user));
        log.info("Created article {}", article);
        return article;
    }

    public void delete(Long id, UserEntity user) {
        ArticleEntity article = findByIdAndUser(id, user);
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
