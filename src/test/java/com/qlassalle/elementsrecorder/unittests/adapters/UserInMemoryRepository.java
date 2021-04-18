package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.exceptions.UserNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInMemoryRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    public Optional<User> findByEmail(String email) {
        return users.stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        return users.stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException(email));
    }
}
