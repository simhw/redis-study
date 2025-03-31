package com.moduleinfra.mapper;

import com.moduledomain.command.domain.theater.Seat;
import com.moduleinfra.entity.theater.SeatJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(source = "no.row", target = "seatNoRow")
    @Mapping(source = "no.number", target = "seatNoNumber")
    SeatJpaEntity toSeatEntity(Seat seat);

    @Mapping(source = "seatNoRow", target = "no.row")
    @Mapping(source = "seatNoNumber", target = "no.number")
    Seat toSeat(SeatJpaEntity seatJpaEntity);
}
