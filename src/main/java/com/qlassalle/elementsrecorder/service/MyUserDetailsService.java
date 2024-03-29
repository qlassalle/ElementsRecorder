package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaUserRepository;
import com.qlassalle.elementsrecorder.domain.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = jpaUserRepository.findByEmail(username)
                                           .orElseThrow(() -> new UsernameNotFoundException("No user exist with this username"));
        return new CustomUserDetails(user);
    }
}
