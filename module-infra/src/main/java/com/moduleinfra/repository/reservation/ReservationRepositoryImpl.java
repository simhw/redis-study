package com.moduleinfra.repository.reservation;

import com.moduledomain.command.domain.reservation.Reservation;
import com.moduledomain.command.domain.reservation.ReservationRepository;
import com.moduleinfra.entity.reservation.ReservationJpaEntity;
import com.moduleinfra.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public Reservation getReservationBy(Long id) {
        ReservationJpaEntity reservationEntity = reservationJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        return reservationMapper.toReservation(reservationEntity);
    }

    @Override
    @Transactional
    public void saveReservation(Reservation reservation) {
        ReservationJpaEntity reservationEntity = reservationMapper.toReservationEntity(reservation);
        reservationJpaRepository.save(reservationEntity);
    }
}
