package com.qlassalle.elementsrecorder.domain.model.repository;

import com.qlassalle.elementsrecorder.domain.model.Resource;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository {
    Resource save(Resource resource);

    List<Resource> findAll(UUID userId);

    Resource findById(UUID resourceId, UUID userId);

    void delete(UUID resourceId, UUID userId);
}
