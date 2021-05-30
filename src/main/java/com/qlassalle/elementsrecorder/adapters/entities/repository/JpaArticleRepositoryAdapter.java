package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.adapters.entities.mappers.ArticleMapper;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class JpaArticleRepositoryAdapter implements ArticleRepository {

    private final JpaArticleRepository jpaArticleRepository;
    private final ArticleMapper articleMapper = new ArticleMapper();

    @Override
    public Article save(Article article) {
        ArticleEntity entity = articleMapper.map(article);
        return articleMapper.map(jpaArticleRepository.save(entity));
    }

    @Override
    public List<Article> findAll(UUID userId) {
        return null;
    }
}
