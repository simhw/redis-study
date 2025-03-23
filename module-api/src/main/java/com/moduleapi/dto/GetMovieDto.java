package com.moduleapi.dto;

import com.moduledomain.command.domain.Genre;
import com.moduledomain.query.dto.FetchMovieCriteria;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class GetMovieDto {
    @Data
    @NoArgsConstructor
    public static class SearchCondition {
        @Size(max = 255)
        private String title;
        private String genre;

        public static FetchMovieCriteria from(SearchCondition condition) {
            return FetchMovieCriteria.builder()
                    .title(condition.title)
                    .genre(Genre.from(condition.getGenre()))
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private MovieDto.V1 movie;
        private List<GetScreeningDto.Response> screenings;
    }
}
