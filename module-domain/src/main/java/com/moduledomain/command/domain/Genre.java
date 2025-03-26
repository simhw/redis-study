package com.moduledomain.command.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public enum Genre {
    SF("공상과학"),
    COMEDY("코미디"),
    ACTION("액션"),
    MUSICAL("뮤지컬"),
    HISTORY("시대극"),
    ROMANCE("로맨스"),
    HORROR("공포");

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public static Genre from(String value) {
        try {
            if (StringUtils.hasText(value)) {
                return Genre.valueOf(value.toUpperCase());
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
