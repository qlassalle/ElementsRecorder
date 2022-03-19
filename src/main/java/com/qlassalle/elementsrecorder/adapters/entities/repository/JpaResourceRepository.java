package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaResourceRepository extends JpaRepository<ResourceEntity, Long> {

    List<ResourceEntity> findAllByUserIdOrderByRatingDesc(UUID userId);

    Optional<ResourceEntity> findByIdAndUserIdOrderByRatingDesc(UUID id, UUID userId);

    void deleteByIdAndUserId(UUID resourceId, UUID userId);
}
