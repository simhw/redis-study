package com.moduleinfra.repository.screening;

import com.moduleinfra.entity.screening.ScreeningJpaEntity;
import org.springframework.data.repository.CrudRepository;

public interface ScreeningJpaRepository extends CrudRepository<ScreeningJpaEntity, Long> {
}
