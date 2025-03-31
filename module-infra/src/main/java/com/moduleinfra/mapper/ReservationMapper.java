package com.moduleinfra.mapper;

import com.moduledomain.command.domain.reservation.Reservation;
import com.moduleinfra.entity.reservation.ReservationJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toReservation(ReservationJpaEntity reservationEntity);

    ReservationJpaEntity toReservationEntity(Reservation reservation);
}
