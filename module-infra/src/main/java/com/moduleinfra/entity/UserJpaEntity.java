package com.moduleinfra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user")
@AllArgsConstructor
@Getter
public class UserJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;

    public UserJpaEntity() {
    }

    public UserJpaEntity(String username) {
        this.username = username;
    }
}
