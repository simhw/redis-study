package com.moduleinfra.mapper;

import com.moduledomain.command.domain.screnning.Screening;
import com.moduleinfra.entity.screening.ScreeningJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    Screening toScreening(ScreeningJpaEntity screeningEntity);

    ScreeningJpaEntity toScreeningEntity(Screening screening);
}
