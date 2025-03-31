package com.moduledomain.command.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationCommand {
    private Long userId;
    private Long screeningId;
    private List<Long> allocatedSeatIds;
}
