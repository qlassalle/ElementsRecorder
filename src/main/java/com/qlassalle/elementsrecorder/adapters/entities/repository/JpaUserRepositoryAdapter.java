package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.mappers.UserMapper;
import com.qlassalle.elementsrecorder.domain.exceptions.UserNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                             .map(userMapper::map);
    }

    @Override
    public User save(User user) {
        return userMapper.map(userRepository.save(userMapper.map(user)));
    }

    @Override
    public User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
}
