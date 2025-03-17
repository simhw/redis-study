package com.moduleapi.controller;

import com.moduleapi.dto.*;
import com.moduledomain.query.application.ScreeningQueryService;
import com.moduledomain.query.dto.MovieSummary;
import com.moduledomain.query.dto.ScreeningInfo;
import com.moduledomain.query.dto.TheaterSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/screenings")
public class ScreeningController {

    private final ScreeningQueryService screeningQueryService;

    @GetMapping("/active")
    public ResponseEntity<List<GetMovieDto.Response>> getActiveScreenings() {
        List<ScreeningInfo> screenings = screeningQueryService.getActiveScreenings();

        Map<Long, GetMovieDto.Response> movies = new HashMap<>();

        for (ScreeningInfo screening : screenings) {
            MovieSummary movie = screening.getMovie();

            GetMovieDto.Response movieResponse = movies.computeIfAbsent(movie.getId(), (id) ->
                    GetMovieDto.Response.builder()
                            .movie(MovieDto.V1.from(movie))
                            .screenings(new ArrayList<>())
                            .build());

            GetScreeningDto.Response screeingResponse = GetScreeningDto.Response.builder()
                    .theater(TheaterDto.V1.from(screening.getTheater()))
                    .screening(ScreeningDto.V1.builder()
                            .id(screening.getId())
                            .startAt(screening.getStartAt())
                            .endAt(screening.getEndAt())
                            .build())
                    .build();

            movieResponse.getScreenings().add(screeingResponse);
        }

        return ResponseEntity.ok(movies.values().stream().toList());

    }
}
