package com.moduledomain.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Reservation {
    private Long id;
    private User user;
    private Long amount;
    private Screening screening;
    private String status;
    private List<Seat> seats;
    private LocalDateTime createdAt;

    public Reservation(Long id) {
        this.id = id;
    }

    // TODO 예매 처리
    public void reserve(User user, Screening screening, String status) {
    }
}
