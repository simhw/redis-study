package com.moduledomain.command.domain;

import org.springframework.util.StringUtils;

public enum Genre {
    SF,
    COMEDY,
    ACTION,
    MUSICAL,
    HISTORY,
    ROMANCE,
    HORROR;

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
