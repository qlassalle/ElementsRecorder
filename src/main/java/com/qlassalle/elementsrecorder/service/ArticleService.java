package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.entities.entity.ArticleEntity;
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

    public List<Article> findAll() {
        return articleRepository.findAll().stream().map(ArticleMapper::map).collect(Collectors.toList());
    }

    public Article findById(Long id) {
        ArticleEntity article = articleRepository.findById(id)
                                                 .orElseThrow(() -> new NoSuchElementException("No such article with " +
                                                                                                       "this id"));
        return ArticleMapper.map(article);
    }

    public Article create(Article article) {
        ArticleEntity articleEntity = articleRepository.save(ArticleMapper.map(article));
        log.info("Saved {}", articleEntity.toString());
        return ArticleMapper.map(articleEntity);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
        log.info("Deleted entity with id {}", id);
    }
}
