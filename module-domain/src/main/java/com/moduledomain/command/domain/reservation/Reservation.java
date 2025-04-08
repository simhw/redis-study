package com.moduledomain.command.domain.reservation;

import com.moduledomain.command.domain.reservation.exception.ReservationErrorType;
import com.moduledomain.command.domain.reservation.exception.ReservationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Reservation {
    private Long id;
    private Integer totalAmount;
    private Long userId;
    private Long screeningId;
    private List<ReservedSeat> reservedSeats;
    private ReservationStatus status;
    private LocalDateTime createdAt;

    public Reservation() {
    }

    public Reservation(Long userId, Long screeningId, List<ReservedSeat> reservedSeats, int price) {
        this.userId = userId;
        this.screeningId = screeningId;
        this.status = ReservationStatus.RESERVED;
        setReservedSeats(reservedSeats);
        this.totalAmount = calculateTotalAmount(reservedSeats.size(), price);
    }

    private void setReservedSeats(List<ReservedSeat> reservedSeats) {
        if (reservedSeats == null) {
            throw new ReservationException(ReservationErrorType.RESERVATION_NOT_FOUND);
        } this.reservedSeats = reservedSeats;
    }

    /**
     * 최종 결제 금액을 계산
     */
    private Integer calculateTotalAmount(int count, int price) {
        return count * price;
    }
}
