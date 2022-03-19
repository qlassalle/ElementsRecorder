package com.qlassalle.elementsrecorder.adapters.entities.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "elements_recorder_schema", name = "resource")
public class ResourceEntity {

    @Id
    private UUID id;

    private String name;

    private String description;

    private int rating;

    private String url;

    private Instant createdAt;

    private Instant updatedAt;

    @Setter
    private UUID userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceEntity that)) {
            return false;
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
