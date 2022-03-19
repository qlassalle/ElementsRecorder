package com.qlassalle.elementsrecorder.domain.usecases.resource;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.input.ResourceCreationRequest;
import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateResourceUseCase {

    private final ResourceRepository resourceRepository;
    private final UUIDProvider uuidProvider;

    public Resource create(ResourceCreationRequest request, Set<UUID> tags, UUID userId) {
        Resource resource = new Resource(uuidProvider.generate(), request.name(), request.description(),
                                         request.rating(), request.url(), tags, userId);
        return resourceRepository.save(resource);
    }
}
