package com.qlassalle.elementsrecorder.domain.usecases.resource;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.input.ResourceCreationRequest;
import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateResourceUseCase {

    private final ResourceRepository resourceRepository;
    private final UUIDProvider uuidProvider;

    public Resource create(ResourceCreationRequest creationRequest, UUID userId) {
        Resource resource = new Resource(uuidProvider.generate(), creationRequest.name(), creationRequest.description(),
                                         creationRequest.rating(), creationRequest.url(), userId);
        return resourceRepository.save(resource);
    }
}
