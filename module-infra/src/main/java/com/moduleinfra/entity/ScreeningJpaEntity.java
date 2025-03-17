package com.moduleinfra.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "screening")
@AllArgsConstructor
public class ScreeningJpaEntity extends BaseJpaEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "screening_id")
    private Long id;
    @Column(name = "movie_id")
    private Long movie;
    @Column(name = "theater_id")
    private Long theater;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    protected ScreeningJpaEntity() {
    }
}
