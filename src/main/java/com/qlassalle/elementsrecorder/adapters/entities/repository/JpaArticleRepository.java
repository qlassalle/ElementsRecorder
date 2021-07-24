package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAllByUserId(UUID userId);

    Optional<ArticleEntity> findByIdAndUserId(UUID id, UUID userId);

    void deleteByIdAndUserId(UUID articleId, UUID userId);
}
