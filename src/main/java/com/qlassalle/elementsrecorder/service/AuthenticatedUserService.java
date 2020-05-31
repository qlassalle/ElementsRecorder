package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.entities.repository.UserRepository;
import com.qlassalle.elementsrecorder.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final UserRepository userRepository;

    public UserEntity getUser(long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> new UserNotFoundException("User doesn't exist"));
    }
}
