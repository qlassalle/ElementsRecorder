package com.qlassalle.elementsrecorder.adapters.entities.mappers;

import com.qlassalle.elementsrecorder.adapters.entities.entity.TagEntity;
import com.qlassalle.elementsrecorder.domain.model.Tag;

public class TagMapper {

    public TagEntity map(Tag tag) {
        return TagEntity.builder()
                        .id(tag.id())
                        .name(tag.name())
                        .userId(tag.userId())
                        .createdAt(tag.createdAt())
                        .updatedAt(tag.updatedAt())
                        .build();
    }

    public Tag map(TagEntity tagEntity) {
        return Tag.builder()
                  .id(tagEntity.getId())
                  .name(tagEntity.getName())
                  .userId(tagEntity.getUserId())
                  .createdAt(tagEntity.getCreatedAt())
                  .updatedAt(tagEntity.getUpdatedAt())
                  .build();
    }

    public Tag mapMinimalInfo(TagEntity tagEntity) {
        return Tag.builder()
                  .id(tagEntity.getId())
                  .name(tagEntity.getName())
                  .build();
    }
}
