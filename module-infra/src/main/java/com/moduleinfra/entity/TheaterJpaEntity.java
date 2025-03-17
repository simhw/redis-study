package com.moduleinfra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "theater")
@AllArgsConstructor
public class TheaterJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "theater_id")
    private Long id;
    private String name;

    protected TheaterJpaEntity() {
    }
}
