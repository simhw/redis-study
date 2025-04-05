package com.moduledomain.command.domain.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long id;
    private String username;
    private LocalDateTime deletedAt;

    @Builder
    public User(Long id, String username, LocalDateTime deletedAt) {
        this.id = id;
        this.username = username;
        this.deletedAt = deletedAt;
    }

    public void validateActiveUser() {
        if (this.deletedAt != null) {
            throw new IllegalArgumentException("유효하지 않은 회원입니다.");
        }
    }
}
