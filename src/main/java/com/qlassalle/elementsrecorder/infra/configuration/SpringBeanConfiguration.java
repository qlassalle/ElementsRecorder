package com.qlassalle.elementsrecorder.infra.configuration;

import com.qlassalle.elementsrecorder.adapters.entities.RandomUUIDProvider;
import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaArticleRepositoryAdapter;
import com.qlassalle.elementsrecorder.domain.usecases.article.CreateArticleUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.article.DeleteArticleUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.article.GetArticleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

    @Bean
    public CreateArticleUseCase createArticleUseCase(JpaArticleRepositoryAdapter articleRepository,
                                                     RandomUUIDProvider uuidProvider) {
        return new CreateArticleUseCase(articleRepository, uuidProvider);
    }

    @Bean
    public GetArticleUseCase getArticleUseCase(JpaArticleRepositoryAdapter articleRepository) {
        return new GetArticleUseCase(articleRepository);
    }

    @Bean
    public DeleteArticleUseCase deleteArticleUseCase(JpaArticleRepositoryAdapter articleRepository) {
        return new DeleteArticleUseCase(articleRepository);
    }
}
