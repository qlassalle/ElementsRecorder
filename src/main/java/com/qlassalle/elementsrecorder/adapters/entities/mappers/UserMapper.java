package com.qlassalle.elementsrecorder.adapters.entities.mappers;

import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.domain.model.User;

public class UserMapper {

    public User map(UserEntity user) {
        return User.builder()
                   .id(user.getId())
                   .email(user.getEmail())
                   .password(user.getPassword())
                   .createdAt(user.getCreatedAt())
                   .updatedAt(user.getUpdatedAt())
                   .build();
    }

    public UserEntity map(User user) {
        return UserEntity.builder()
                         .id(user.getId())
                         .email(user.getEmail())
                         .password(user.getPassword())
                         .createdAt(user.getCreatedAt())
                         .updatedAt(user.getUpdatedAt())
                         .build();
    }
}
