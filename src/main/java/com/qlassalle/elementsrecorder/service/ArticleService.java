package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.entities.repository.ArticleRepository;
import com.qlassalle.elementsrecorder.model.Article;
import com.qlassalle.elementsrecorder.model.mappers.ArticleMapper;
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

    private final ArticleRepository articleRepository;

    public List<Article> findAll(UserEntity user) {
        return articleRepository.findAllByUser(user)
                                .stream()
                                .map(ArticleMapper::map)
                                .collect(Collectors.toList());
    }

    public Article findById(Long id, UserEntity user) {
        ArticleEntity article = findByIdAndUser(id, user);
        return ArticleMapper.map(article);
    }

    private ArticleEntity findByIdAndUser(Long id, UserEntity user) {
        return articleRepository.findByIdAndUser(id, user)
                                .orElseThrow(() -> new NoSuchElementException("No such article with this id"));
    }

    public Article create(Article article, UserEntity user) {
        ArticleEntity articleEntity = ArticleMapper.map(article, user);
        ArticleEntity savedEntity = articleRepository.save(articleEntity);
        log.info("Saved {}", articleEntity.toString());
        return ArticleMapper.map(savedEntity);
    }

    public void delete(Long id, UserEntity user) {
        ArticleEntity article = findByIdAndUser(id, user);
        articleRepository.delete(article);
        log.info("Deleted entity with id {}", id);
    }

    public Article update(Long id, Article article, UserEntity user) {
        findByIdAndUser(id, user);
        article.setId(id);
        ArticleEntity entity = articleRepository.save(ArticleMapper.map(article, user));
        log.info("Updated {}", article.toString());
        return ArticleMapper.map(entity);
    }
}
