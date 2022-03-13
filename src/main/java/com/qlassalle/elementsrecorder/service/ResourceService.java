package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.input.ResourceCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.resource.CreateResourceUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.resource.DeleteResourceUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.resource.GetResourceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final CreateResourceUseCase createResourceUseCase;
    private final GetResourceUseCase getResourceUseCase;
    private final DeleteResourceUseCase deleteResourceUseCase;

    public List<Resource> findAll(UUID userId) {
        return getResourceUseCase.findAll(userId);
    }

    public Resource findById(UUID id, UserEntity user) {
        return getResourceUseCase.findById(id, user.getId());
    }

    public Resource create(ResourceCreationRequest creationRequest, UUID userId) {
        return createResourceUseCase.create(creationRequest, userId);
    }

    @Transactional
    public void delete(UUID id, UserEntity user) {
        deleteResourceUseCase.delete(id, user.getId());
    }

//    public Resource update(Long id, Resource resource, UserEntity user) {
//        findByIdAndUser(id, user);
//        resource.setId(id);
//        ResourceEntity entity = resourceRepository.save(ResourceMapper.map(resource, user));
//        log.info("Updated {}", resource.toString());
//        return ResourceMapper.map(entity);
//    }
}
