package com.moduleapi.dto;

import com.moduledomain.query.dto.ScreeningInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ScreeningDto {
    @Getter
    @Builder
    public static class V1 {
        private Long id;
        private LocalDateTime startAt;
        private LocalDateTime endAt;

        public static ScreeningDto.V1 from(ScreeningInfo screening) {
            return ScreeningDto.V1.builder()
                    .id(screening.getId())
                    .startAt(screening.getStartAt())
                    .endAt(screening.getEndAt())
                    .build();
        }
    }
}
