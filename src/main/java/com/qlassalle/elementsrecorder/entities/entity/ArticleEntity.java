package com.qlassalle.elementsrecorder.entities.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "article")
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
}
