package com.qlassalle.elementsrecorder.adapters.entities.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "elements_recorder_schema", name = "article")
public class ArticleEntity {

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
        if (!(o instanceof ArticleEntity that)) {
            return false;
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
