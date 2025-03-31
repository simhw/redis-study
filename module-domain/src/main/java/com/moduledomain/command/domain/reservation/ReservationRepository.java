package com.moduledomain.command.domain.reservation;

public interface ReservationRepository {
    public Reservation getReservationBy(Long id);

    public void saveReservation(Reservation reservation);
}
