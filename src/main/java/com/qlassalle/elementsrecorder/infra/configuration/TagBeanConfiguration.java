package com.qlassalle.elementsrecorder.infra.configuration;

import com.qlassalle.elementsrecorder.adapters.entities.RandomUUIDProvider;
import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaTagRepositoryAdapter;
import com.qlassalle.elementsrecorder.domain.usecases.tag.GetOrCreateTagUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TagBeanConfiguration {

    @Bean
    public GetOrCreateTagUseCase getOrCreateTagUseCase(JpaTagRepositoryAdapter tagRepository) {
        return new GetOrCreateTagUseCase(tagRepository, new RandomUUIDProvider());
    }
}
