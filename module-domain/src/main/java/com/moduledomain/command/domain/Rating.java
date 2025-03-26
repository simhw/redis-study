package com.moduledomain.command.domain;

import lombok.Getter;

@Getter
public enum Rating {
    G("전체 연령"),
    R12("12세 이상"),
    R15("15세 이상"),
    R18("18세 이상");

    private final String description;

    Rating(String description) {
        this.description = description;
    }
}
