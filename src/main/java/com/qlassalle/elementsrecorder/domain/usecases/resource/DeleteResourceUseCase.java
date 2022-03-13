package com.qlassalle.elementsrecorder.domain.usecases.resource;

import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteResourceUseCase {

    private final ResourceRepository resourceRepository;

    public void delete(UUID resourceId, UUID userId) {
        resourceRepository.delete(resourceId, userId);
    }
}
