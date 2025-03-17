package com.moduledomain.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScreeningInfo {
    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private MovieSummary movie;
    private TheaterSummary theater;
}
