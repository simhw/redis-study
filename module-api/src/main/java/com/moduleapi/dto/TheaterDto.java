package com.moduleapi.dto;

import com.moduledomain.query.dto.TheaterSummary;
import lombok.Builder;
import lombok.Getter;

public class TheaterDto {
    @Getter
    @Builder
    public static class V1 {
        private Long id;
        private String name;

        public static TheaterDto.V1 from(TheaterSummary theater) {
            return TheaterDto.V1.builder()
                    .id(theater.getId())
                    .name(theater.getName())
                    .build();
        }
    }
}
