package com.moduleinfra.repository.screening;

import com.moduledomain.command.domain.screnning.AllocatedSeat;
import com.moduledomain.command.domain.screnning.Screening;
import com.moduledomain.command.domain.screnning.ScreeningRepository;
import com.moduleinfra.entity.screening.AllocatedSeatJpaEntity;
import com.moduleinfra.entity.screening.ScreeningJpaEntity;
import com.moduleinfra.mapper.AllocatedSeatMapper;
import com.moduleinfra.mapper.ScreeningMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScreeningRepositoryImpl implements ScreeningRepository {
    private final ScreeningJpaRepository screeningJpaRepository;
    private final AllocatedSeatJpaRepository allocatedSeatJpaRepository;
    private final ScreeningMapper screeningMapper;
    private AllocatedSeatMapper allocatedSeatMapper;

    @Override
    public Screening getScreeningBy(Long id) {
        ScreeningJpaEntity screeningEntity = screeningJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Screening not found"));
        return screeningMapper.toScreening(screeningEntity);
    }

    @Override
    public List<AllocatedSeat> getAllocatedSeatsBy(List<Long> allocatedSeatIds) {
        List<AllocatedSeat> allocatedSeats = new ArrayList<>();
        allocatedSeatJpaRepository.findAllById(allocatedSeatIds).iterator().forEachRemaining(allocatedSeatJpaEntity -> {
            allocatedSeats.add(allocatedSeatMapper.toAllocatedSeat(allocatedSeatJpaEntity));
        });
        return allocatedSeats;
    }

    @Override
    @Transactional
    public void saveAllocatedSeats(List<AllocatedSeat> allocatedSeats) {
        List<AllocatedSeatJpaEntity> allocatedSeatEntities = allocatedSeats.stream()
                .map(allocatedSeatMapper::toAllocatedSeatEntity)
                .toList();
        allocatedSeatJpaRepository.saveAll(allocatedSeatEntities);
    }
}
