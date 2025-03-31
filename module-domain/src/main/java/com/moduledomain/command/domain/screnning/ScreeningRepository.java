package com.moduledomain.command.domain.screnning;

import java.util.List;

public interface ScreeningRepository {
    public Screening getScreeningBy(Long id);

    public List<AllocatedSeat> getAllocatedSeatsBy(List<Long> allocatedSeatIds);

    public void saveAllocatedSeats(List<AllocatedSeat> allocatedSeats);
}


