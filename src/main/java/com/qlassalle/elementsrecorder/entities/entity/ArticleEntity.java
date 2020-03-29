package com.qlassalle.elementsrecorder.entities.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Table(name = "article")
public class ArticleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String description;

    private short rating;

    private Instant createdAt;

    private Instant updatedAt;
}
