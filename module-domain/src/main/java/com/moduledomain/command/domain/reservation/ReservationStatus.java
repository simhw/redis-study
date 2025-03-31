package com.moduledomain.command.domain.reservation;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    RESERVED("예약완료"),
    CANCELED("취소됨");

    private final String description;

    ReservationStatus(String description) {
        this.description = description;
    }
}
