package com.qlassalle.elementsrecorder.entities.repository;

import com.qlassalle.elementsrecorder.entities.entity.ArticleEntity;
import com.qlassalle.elementsrecorder.entities.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAllByUser(UserEntity user);

    @Query("SELECT a FROM ArticleEntity a LEFT JOIN FETCH a.user WHERE a.id = :id AND a.user = :user")
    Optional<ArticleEntity> findByIdAndUser(Long id, UserEntity user);
}
