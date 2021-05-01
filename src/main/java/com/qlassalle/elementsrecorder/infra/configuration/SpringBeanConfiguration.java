package com.qlassalle.elementsrecorder.infra.configuration;

import com.qlassalle.elementsrecorder.adapters.entities.RandomUUIDProvider;
import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaArticleRepositoryAdapter;
import com.qlassalle.elementsrecorder.domain.usecases.article.CreateArticleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

    @Bean
    public CreateArticleUseCase createArticleUseCase(JpaArticleRepositoryAdapter articleRepository,
                                                     RandomUUIDProvider uuidProvider) {
        return new CreateArticleUseCase(articleRepository, uuidProvider);
    }
}