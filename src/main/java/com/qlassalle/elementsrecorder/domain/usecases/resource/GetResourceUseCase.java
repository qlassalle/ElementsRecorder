package com.qlassalle.elementsrecorder.domain.usecases.resource;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetResourceUseCase {

    private final ResourceRepository resourceRepository;

    public List<Resource> findAll(UUID userId) {
        return resourceRepository.findAll(userId);
    }

    public Resource findById(UUID resourceId, UUID userId) {
        return resourceRepository.findById(resourceId, userId);
    }
}
