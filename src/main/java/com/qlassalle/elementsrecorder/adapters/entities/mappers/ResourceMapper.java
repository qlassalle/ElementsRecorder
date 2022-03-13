package com.qlassalle.elementsrecorder.adapters.entities.mappers;

import com.qlassalle.elementsrecorder.adapters.entities.entity.ResourceEntity;
import com.qlassalle.elementsrecorder.domain.model.Resource;

public class ResourceMapper {

    public Resource map(ResourceEntity resourceEntity) {
        return Resource.builder()
                       .id(resourceEntity.getId())
                       .description(resourceEntity.getDescription())
                       .name(resourceEntity.getName())
                       .rating(resourceEntity.getRating())
                       .url(resourceEntity.getUrl())
                       .userId(resourceEntity.getUserId())
                       .createdAt(resourceEntity.getCreatedAt())
                       .updatedAt(resourceEntity.getUpdatedAt())
                       .build();
    }

    public ResourceEntity map(Resource resource) {
        var builder = ResourceEntity.builder();
        if (resource.getId() != null) {
            builder.id(resource.getId());
        }
        return builder.name(resource.getName())
                      .description(resource.getDescription())
                      .rating(resource.getRating())
                      .url(resource.getUrl())
                      .userId(resource.getUserId())
                      .createdAt(resource.getCreatedAt())
                      .updatedAt(resource.getUpdatedAt())
                      .build();
    }
}
