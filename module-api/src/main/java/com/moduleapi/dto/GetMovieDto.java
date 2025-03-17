package com.moduleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class GetMovieDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private MovieDto.V1 movie;
        private List<GetScreeningDto.Response> screenings;
    }
}
