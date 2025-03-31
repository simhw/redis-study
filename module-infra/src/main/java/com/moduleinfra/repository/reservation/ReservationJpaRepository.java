package com.moduleinfra.repository.reservation;

import com.moduleinfra.entity.reservation.ReservationJpaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationJpaRepository extends CrudRepository<ReservationJpaEntity, Long> {
    List<ReservationJpaEntity> findByScreeningId(Long screeningId);
}
