package com.moduleinfra.repository.screening;

import com.moduleinfra.entity.screening.AllocatedSeatJpaEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AllocatedSeatJpaRepository extends CrudRepository<AllocatedSeatJpaEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from AllocatedSeatJpaEntity a where a.id in(:ids)")
    Iterable<AllocatedSeatJpaEntity> findAllByIdWithPessimisticLock(List<Long> ids);
}
