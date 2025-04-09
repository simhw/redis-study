package com.moduleapi.controller;

import com.modulecommon.annotation.RateLimit;
import com.moduleapi.dto.*;
import com.moduledomain.query.application.ScreeningQueryService;
import com.moduledomain.query.dto.FetchMovieCriteria;
import com.moduledomain.query.dto.MovieSummary;
import com.moduledomain.query.dto.ScreeningInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movies")
public class MovieController {
    private final ScreeningQueryService screeningQueryService;

    // ip별로 1시간 동안 50회 요청 가능
    @RateLimit(
            key = "'now-showing:' + #ip",
            limitCount = 50,
            limitTimeSecond = 3600  // 60 * 60 = 1시간
    )
    @GetMapping("/now-showing")
    public ResponseEntity<List<GetMovieDto.Response>> getNowShowingMovies(
            @Validated @ModelAttribute GetMovieDto.SearchCondition condition, @RequestAttribute("client-ip") String ip
    ) {
        FetchMovieCriteria criteria = GetMovieDto.SearchCondition.from(condition);
        List<ScreeningInfo> infos = screeningQueryService.getActiveScreenings(criteria);

        List<GetMovieDto.Response> responses = infos.stream()
                .collect(Collectors.groupingBy(
                        info -> info.getMovie().getId(),
                        LinkedHashMap::new,
                        Collectors.collectingAndThen(Collectors.toList(), screenings -> {
                            MovieSummary movie = screenings.getFirst().getMovie();
                            List<GetScreeningDto.Response> screening = screenings.stream()
                                    .map(info -> GetScreeningDto.Response.builder()
                                            .theater(TheaterDto.V1.from(info.getTheater()))
                                            .screening(ScreeningDto.V1.from(info))
                                            .build())
                                    .toList();

                            return GetMovieDto.Response.builder()
                                    .movie(MovieDto.V1.from(movie))
                                    .screenings(screening)
                                    .build();
                        })
                ))
                .values()
                .stream()
                .toList();

        return ResponseEntity.ok(responses);
    }
}
