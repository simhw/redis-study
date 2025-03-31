package com.moduleinfra.repository.reservation;

import com.moduleinfra.entity.reservation.ReservationJpaEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationJpaRepository extends CrudRepository<ReservationJpaEntity, Long> {
}
