package com.qlassalle.elementsrecorder.infra.configuration;

import com.qlassalle.elementsrecorder.adapters.entities.RandomUUIDProvider;
import com.qlassalle.elementsrecorder.adapters.entities.repository.JpaResourceRepositoryAdapter;
import com.qlassalle.elementsrecorder.domain.usecases.resource.CreateResourceUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.resource.DeleteResourceUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.resource.GetResourceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

    @Bean
    public CreateResourceUseCase createResourceUseCase(JpaResourceRepositoryAdapter resourceRepository,
                                                       RandomUUIDProvider uuidProvider) {
        return new CreateResourceUseCase(resourceRepository, uuidProvider);
    }

    @Bean
    public GetResourceUseCase getResourceUseCase(JpaResourceRepositoryAdapter resourceRepository) {
        return new GetResourceUseCase(resourceRepository);
    }

    @Bean
    public DeleteResourceUseCase deleteResourceUseCase(JpaResourceRepositoryAdapter resourceRepository) {
        return new DeleteResourceUseCase(resourceRepository);
    }
}
