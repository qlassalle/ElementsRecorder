package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
