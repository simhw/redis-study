package com.moduledomain.command.domain.theater;

import java.util.List;

public interface SeatRepository {
    Seat getSeatBy(Long id);

    List<Seat> getAllSeatBy(List<Long> ids);
}
