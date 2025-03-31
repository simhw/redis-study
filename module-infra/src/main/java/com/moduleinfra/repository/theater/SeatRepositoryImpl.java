package com.moduleinfra.repository.theater;

import com.moduledomain.command.domain.theater.Seat;
import com.moduledomain.command.domain.theater.SeatRepository;
import com.moduleinfra.entity.theater.SeatJpaEntity;
import com.moduleinfra.mapper.SeatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {
    private final SeatJpaRepository seatJpaRepository;
    private final SeatMapper seatMapper;

    @Override
    public Seat getSeatBy(Long id) {
        SeatJpaEntity seatEntity = seatJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        return seatMapper.toSeat(seatEntity);
    }

    @Override
    public List<Seat> getAllSeatBy(List<Long> ids) {
        Iterable<SeatJpaEntity> seatEntities = seatJpaRepository.findAllById(ids);
        List<Seat> seats = new ArrayList<>();
        seatEntities.iterator().forEachRemaining(seatJpaEntity -> {
            Seat seat = seatMapper.toSeat(seatJpaEntity);
            seats.add(seat);
        });
        return seats;
    }
}
