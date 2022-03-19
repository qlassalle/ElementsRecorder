package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.exceptions.ResourceNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.repository.ResourceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InMemoryResourceRepository implements ResourceRepository {

    private final List<Resource> resources = new ArrayList<>();

    @Override
    public Resource save(Resource resource) {
        resources.add(resource);
        return resource;
    }

    @Override
    public List<Resource> findAll(UUID userId) {
        return resources.stream()
                        .filter(resource -> resource.getUserId()
                                                    .equals(userId))
                        .collect(Collectors.toList());
    }

    @Override
    public Resource findById(UUID resourceId, UUID userId) {
        return resources.stream()
                        .filter(resource -> resource.getId().equals(resourceId) && resource.getUserId().equals(userId))
                        .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException("No resource found with id %s", resourceId));
    }

    @Override
    public void delete(UUID resourceId, UUID userId) {
        resources.removeIf(resource -> resource.getId().equals(resourceId) && resource.getUserId().equals(userId));
    }
}
