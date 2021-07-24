package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.adapters.entities.mappers.ArticleMapper;
import com.qlassalle.elementsrecorder.domain.exceptions.ResourceNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return jpaArticleRepository.findAllByUserId(userId)
                                   .stream()
                                   .map(articleMapper::map)
                                   .collect(Collectors.toList());
    }

    @Override
    public Article findById(UUID articleId, UUID userId) {
        Optional<ArticleEntity> articleEntity = jpaArticleRepository.findByIdAndUserId(articleId, userId);
        return articleEntity.map(articleMapper::map)
                            .orElseThrow(() -> new ResourceNotFoundException("No article found for id %s", articleId));
    }

    @Override
    public void delete(UUID articleId, UUID userId) {
        jpaArticleRepository.deleteByIdAndUserId(articleId, userId);
    }
}
