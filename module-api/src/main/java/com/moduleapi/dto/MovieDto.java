package com.moduleapi.dto;

import com.moduledomain.query.dto.MovieSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MovieDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class V1 {
        private Long id;
        private String title;
        private String ratings;
        private String thumbnail;
        private Integer runtime;
        private String genre;
        private LocalDateTime releaseAt;

        public static MovieDto.V1 from(MovieSummary movie) {
            return V1.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .ratings(movie.getRating().name())
                    .thumbnail(movie.getThumbnail())
                    .runtime(movie.getRuntime())
                    .genre(movie.getGenre().name())
                    .releaseAt(movie.getReleaseAt())
                    .build();
        }
    }
}
