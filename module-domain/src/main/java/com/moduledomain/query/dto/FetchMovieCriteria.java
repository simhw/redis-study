package com.moduledomain.query.dto;

import com.moduledomain.command.domain.screnning.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FetchMovieCriteria {
    private String title;
    private Genre genre;
}
