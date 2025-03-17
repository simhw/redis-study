package com.moduledomain.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Screening {
    private Long id;
    private Movie movie;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Theater theater;

    public Screening(Long id, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void setMovie(Movie movie) {
        if (movie == null) {
            throw new RuntimeException("empty movie");
        }
        this.movie = movie;
    }
}
