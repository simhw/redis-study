package com.moduledomain.command.domain.screnning;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllocatedSeat {
    private Long id;
    private Long seatId;
    private Long screeningId;
    private boolean reserved;

    public void reserve() {
        this.reserved = true;
    }
}
