package com.qlassalle.elementsrecorder.entities.repository;

import com.qlassalle.elementsrecorder.entities.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
