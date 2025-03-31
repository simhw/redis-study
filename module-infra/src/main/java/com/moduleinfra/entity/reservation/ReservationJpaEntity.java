package com.moduleinfra.entity.reservation;

import com.moduledomain.command.domain.reservation.ReservationStatus;
import com.moduledomain.command.domain.reservation.ReservedSeat;
import com.moduleinfra.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "reservation")
@AllArgsConstructor
public class ReservationJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "screening_id")
    private Long screeningId;
    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;

    @ElementCollection
    @CollectionTable(
            name = "reserved_seat",
            joinColumns = @JoinColumn(name = "reservation_id"),
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private List<ReservedSeatJpaEntity> reservedSeats = new ArrayList<>();

    protected ReservationJpaEntity() {
    }
}
