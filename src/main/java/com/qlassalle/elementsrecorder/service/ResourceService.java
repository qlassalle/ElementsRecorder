package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.adapters.entities.entity.UserEntity;
import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.model.input.ResourceCreationRequest;
import com.qlassalle.elementsrecorder.domain.model.output.ResourceResponse;
import com.qlassalle.elementsrecorder.domain.usecases.resource.CreateResourceUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.resource.DeleteResourceUseCase;
import com.qlassalle.elementsrecorder.domain.usecases.resource.GetResourceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final CreateResourceUseCase createResourceUseCase;
    private final GetResourceUseCase getResourceUseCase;
    private final DeleteResourceUseCase deleteResourceUseCase;
    private final TagService tagService;
    private final TagResourceDAO tagResourceDAO;

    public List<ResourceResponse> findAll(UUID userId) {
        var resources = getResourceUseCase.findAll(userId);
        var response = new LinkedList<ResourceResponse>();
        for (var resource : resources) {
            var tags = tagResourceDAO.findByResourceId(resource.getId());
            response.add(new ResourceResponse(resource, tags));
        }

        return response;
    }

    public ResourceResponse findById(UUID id, UUID userId) {
        var resource = getResourceUseCase.findById(id, userId);
        var tags = tagResourceDAO.findByResourceId(id);
        return new ResourceResponse(resource, tags);
    }

    public ResourceResponse create(ResourceCreationRequest creationRequest, UUID userId) {
        var tags = tagService.getOrCreate(creationRequest.tags(), userId);
        var tagsId = tags.stream().map(Tag::id).collect(Collectors.toSet());
        var resource = createResourceUseCase.create(creationRequest, tagsId, userId);
        tagResourceDAO.save(resource.getId(), tagsId);
        return new ResourceResponse(resource, tags);
    }

    @Transactional
    public void delete(UUID id, UserEntity user) {
        deleteResourceUseCase.delete(id, user.getId());
        tagResourceDAO.delete(id);
    }

//    public Resource update(Long id, Resource resource, UserEntity user) {
//        findByIdAndUser(id, user);
//        resource.setId(id);
//        ResourceEntity entity = resourceRepository.save(ResourceMapper.map(resource, user));
//        log.info("Updated {}", resource.toString());
//        return ResourceMapper.map(entity);
//    }
}
