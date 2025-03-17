package com.moduledomain.query.dto;


import com.moduledomain.command.domain.Genre;
import com.moduledomain.command.domain.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MovieSummary {
    private Long id;
    private String title;
    private Rating rating;
    private LocalDateTime releaseAt;
    private String thumbnail;
    private Integer runtime;
    private Genre genre;
}
