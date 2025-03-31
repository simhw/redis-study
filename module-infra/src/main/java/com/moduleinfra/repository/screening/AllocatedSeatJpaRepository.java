package com.moduleinfra.repository.screening;

import com.moduleinfra.entity.screening.AllocatedSeatJpaEntity;
import org.springframework.data.repository.CrudRepository;


public interface AllocatedSeatJpaRepository extends CrudRepository<AllocatedSeatJpaEntity, Long> {
}
