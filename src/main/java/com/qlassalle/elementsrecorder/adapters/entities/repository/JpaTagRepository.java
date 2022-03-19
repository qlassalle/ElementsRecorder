package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaTagRepository extends JpaRepository<TagEntity, UUID> {
    Optional<TagEntity> findByNameAndUserId(String name, UUID userId);
}
