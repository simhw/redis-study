package com.moduleinfra.mapper;

import com.moduledomain.command.domain.screnning.AllocatedSeat;
import com.moduleinfra.entity.screening.AllocatedSeatJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AllocatedSeatMapper {
    AllocatedSeatMapper INSTANCE = Mappers.getMapper(AllocatedSeatMapper.class);

    AllocatedSeat toAllocatedSeat(AllocatedSeatJpaEntity allocatedSeatJpaEntity);

    AllocatedSeatJpaEntity toAllocatedSeatEntity(AllocatedSeat allocatedSeat);
}
