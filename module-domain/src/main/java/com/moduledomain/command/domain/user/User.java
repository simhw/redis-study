package com.moduledomain.command.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String username;

    @Builder
    public User(Long id, String username) {
        this.username = username;
    }
}
