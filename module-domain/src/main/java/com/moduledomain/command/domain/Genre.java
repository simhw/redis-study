package com.moduledomain.command.domain;

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
            return Genre.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
