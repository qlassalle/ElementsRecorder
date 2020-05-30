package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.entities.repository.UserRepository;
import com.qlassalle.elementsrecorder.exceptions.EmailExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void register(String email, String password) {
        if (repository.findByEmail(email).isPresent()) {
            throw new EmailExistsException("There is already an existing account for the address " + email);
        }
        UserEntity user = UserEntity.builder()
                                    .email(email)
                                    .password(passwordEncoder.encode(password))
                                    .userUuid(UUID.randomUUID())
                                    .build();
        repository.save(user);
    }
}
