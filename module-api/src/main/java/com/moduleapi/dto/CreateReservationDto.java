package com.moduleapi.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class CreateReservationDto {
    @Getter
    @AllArgsConstructor
    public static class Request {
        private Long screeningId;
        @Size(min = 1, max = 5)
        private List<Long> allocatedSeatIds;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private ReservationDto reservation;
    }
}
