package com.moduleinfra.entity.screening;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

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
    @Version
    @ColumnDefault("0")
    private Integer version;
    private Long screeningId;
    private Boolean reserved;

    protected AllocatedSeatJpaEntity() {
    }
}
