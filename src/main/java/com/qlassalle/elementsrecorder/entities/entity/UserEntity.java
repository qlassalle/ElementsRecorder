package com.qlassalle.elementsrecorder.entities.entity;

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
@Table(schema = "elements_recorder_schema", name = "user_app")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private UUID userUuid;

    private String email;

    private String password;

    @Column(insertable = false, updatable = false)
    private Instant createdAt;

    @Column(insertable = false, updatable = false)
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }

        UserEntity that = (UserEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
