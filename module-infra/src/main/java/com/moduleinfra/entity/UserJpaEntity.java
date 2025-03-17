package com.moduleinfra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user")
@AllArgsConstructor
public class UserJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;

    protected UserJpaEntity() {

    }
}
