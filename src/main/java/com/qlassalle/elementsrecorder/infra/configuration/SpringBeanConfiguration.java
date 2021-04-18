package com.qlassalle.elementsrecorder.infra.configuration;

import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaUserRepository;
import org.springframework.context.annotation.Bean;

public class SpringBeanConfiguration {

    @Bean
    public JpaUserRepository userRepository(JpaUserRepository userRepository) {
        return userRepository;
    }
}
