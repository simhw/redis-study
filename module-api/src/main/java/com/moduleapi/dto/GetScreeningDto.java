package com.moduleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class GetScreeningDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        public ScreeningDto.V1 screening;
        public TheaterDto.V1 theater;
    }
}
