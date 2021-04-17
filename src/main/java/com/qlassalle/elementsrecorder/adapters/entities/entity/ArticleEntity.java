package com.qlassalle.elementsrecorder.adapters.entities.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "elements_recorder_schema", name = "article")
public class ArticleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String description;

    private short rating;

    private String url;

    @Column(insertable = false, updatable = false)
    private Instant createdAt;

    @Column(insertable = false, updatable = false)
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleEntity)) {
            return false;
        }

        ArticleEntity that = (ArticleEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
