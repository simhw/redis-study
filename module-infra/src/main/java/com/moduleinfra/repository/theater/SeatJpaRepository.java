package com.moduleinfra.repository.theater;

import com.moduleinfra.entity.theater.SeatJpaEntity;
import org.springframework.data.repository.CrudRepository;

public interface SeatJpaRepository extends CrudRepository<SeatJpaEntity, Long> {
}
