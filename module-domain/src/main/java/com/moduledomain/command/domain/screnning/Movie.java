package com.moduledomain.command.domain.screnning;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private Genre genre;
    private Rating rating;
    private Integer runtime;
    private String thumbnail;
    private LocalDateTime releasedAt;

    @Builder
    public Movie(String title, Genre genre, Rating rating, Integer runtime, String thumbnail, LocalDateTime releasedAt) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.runtime = runtime;
        this.thumbnail = thumbnail;
        this.releasedAt = releasedAt;
    }
}
