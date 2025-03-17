package com.moduledomain.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private Genre genre;
    private Rating rating;
    private Long fee;
    private Integer runtime;
    private String thumbnail;
    private LocalDateTime releasedAt;
    private List<Screening> screenings;

    public Movie(
            Long id,
            String title,
            Genre genre,
            Rating rating,
            Integer runtime,
            String thumbnail,
            LocalDateTime releasedAt
    ) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.runtime = runtime;
        this.thumbnail = thumbnail;
        this.releasedAt = releasedAt;
    }
}
