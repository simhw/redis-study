package com.moduleinfra.entity.theater;

import com.moduleinfra.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "seat")
@AllArgsConstructor
public class SeatJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "seat_id")
    private Long id;
    private Character seatNoRow;
    private Integer seatNoNumber;
    @Column(name = "theater_id")
    private Long theaterId;

    protected SeatJpaEntity() {
    }
}
