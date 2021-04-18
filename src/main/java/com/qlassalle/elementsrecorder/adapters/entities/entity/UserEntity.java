package com.qlassalle.elementsrecorder.adapters.entities.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "elements_recorder_schema", name = "user_app")
public class UserEntity {
    @Id
    private UUID id;
    private String email;
    private String password;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && email.equals(that.email) && createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, createdAt);
    }
}
