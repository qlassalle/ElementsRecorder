package com.qlassalle.elementsrecorder.adapters.entities.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "elements_recorder_schema", name = "tag")
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagEntity {

    @Id
    private UUID id;
    private String name;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
}
