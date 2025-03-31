package com.moduledomain.command.domain.theater;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seat {
    private Long id;
    private SeatNo no;
    private Long theaterId;

    @Builder
    public Seat(SeatNo no, Long theaterId) {
        this.no = no;
        this.theaterId = theaterId;
    }
}
