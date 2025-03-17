package com.moduleinfra.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reservation")
public class ReservationJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    @Column(name = "user_id")
    private Long user;
    @Column(name = "screening_id")
    private Long screening;
    @Column(name = "seat_id")
    private Long seat;
}
