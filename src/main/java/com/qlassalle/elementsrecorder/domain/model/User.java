package com.qlassalle.elementsrecorder.domain.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {

    UUID id;
    String email;
    String password;
    Instant createdAt;
    Instant updatedAt;

    public User(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
