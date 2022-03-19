package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ResourceEntity;
import com.qlassalle.elementsrecorder.adapters.entities.mappers.ResourceMapper;
import com.qlassalle.elementsrecorder.domain.exceptions.ResourceNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JpaResourceRepositoryAdapter implements ResourceRepository {

    private final JpaResourceRepository jpaResourceRepository;
    private final ResourceMapper resourceMapper = new ResourceMapper();

    @Override
    public Resource save(Resource resource) {
        ResourceEntity entity = resourceMapper.map(resource);
        return resourceMapper.map(jpaResourceRepository.save(entity));
    }

    @Override
    public List<Resource> findAll(UUID userId) {
        return jpaResourceRepository.findAllByUserIdOrderByRatingDesc(userId)
                                    .stream()
                                    .map(resourceMapper::map)
                                    .collect(Collectors.toList());
    }

    @Override
    public Resource findById(UUID resourceId, UUID userId) {
        Optional<ResourceEntity> resourceEntity = jpaResourceRepository.findByIdAndUserIdOrderByRatingDesc(resourceId, userId);
        return resourceEntity.map(resourceMapper::map)
                            .orElseThrow(() -> new ResourceNotFoundException("No resource found for id %s", resourceId));
    }

    @Override
    public void delete(UUID resourceId, UUID userId) {
        jpaResourceRepository.deleteByIdAndUserId(resourceId, userId);
    }
}
