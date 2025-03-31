package com.moduleinfra.entity.screening;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@Table(name = "allocated_seat")
@AllArgsConstructor
public class AllocatedSeatJpaEntity {
    @Id
    @Column(name = "allocated_seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seatId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "screening_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private ScreeningJpaEntity screening;
    private Long screeningId;
    private Boolean reserved;

    protected AllocatedSeatJpaEntity() {
    }
}
