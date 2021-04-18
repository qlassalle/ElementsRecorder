package com.qlassalle.elementsrecorder.domain.model.repository;

import com.qlassalle.elementsrecorder.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    User save(User user);

    User getByEmail(String email);
}
