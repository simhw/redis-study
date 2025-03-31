package com.moduleinfra.repository.reservation;

import com.moduledomain.command.domain.reservation.Reservation;
import com.moduledomain.command.domain.reservation.ReservationRepository;
import com.moduleinfra.entity.reservation.ReservationJpaEntity;
import com.moduleinfra.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationMapper reservationMapper;

    public List<Reservation> getReservationsByScreeningId(Long screeningId) {
        return reservationJpaRepository.findByScreeningId(screeningId).stream()
                .map(reservationMapper::toReservation)
                .toList();
    }

    @Override
    public Reservation getReservationBy(Long id) {
        ReservationJpaEntity reservationEntity = reservationJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        return reservationMapper.toReservation(reservationEntity);
    }

    @Override
    @Transactional
    public Long saveReservation(Reservation reservation) {
        ReservationJpaEntity reservationEntity = reservationMapper.toReservationEntity(reservation);
        reservationJpaRepository.save(reservationEntity);
        return reservationEntity.getId();
    }
}
