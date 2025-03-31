package com.moduleinfra.entity.reservation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Embeddable
public class ReservedSeatJpaEntity {
    @Column(name = "allocated_seat_id")
    private Long allocatedSeatId;

    protected ReservedSeatJpaEntity() {
    }
}
