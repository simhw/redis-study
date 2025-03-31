package com.moduledomain.command.domain.reservation;

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

    public Reservation(Long userId, Long screeningId, List<ReservedSeat> reservedSeats) {
        this.userId = userId;
        this.screeningId = screeningId;
        this.status = ReservationStatus.RESERVED;
        setReservedSeats(reservedSeats);
    }

    private void setReservedSeats(List<ReservedSeat> reservedSeats) {
        validateLeastSeat(reservedSeats);
        validateMaximumSeat(reservedSeats);
        this.reservedSeats = reservedSeats;
    }

    private void validateLeastSeat(List<ReservedSeat> reservedSeats) {
        if (reservedSeats.isEmpty()) {
            throw new IllegalArgumentException("한 자리 이상 예약 가능합니다.");
        }
    }

    private void validateMaximumSeat(List<ReservedSeat> reservedSeats) {
        if (reservedSeats.size() > 5) {
            throw new IllegalArgumentException("최대 다섯 자리까지 예약 가능합니다.");
        }
    }

    /**
     * 최종 결제 금액을 계산
     */
    private Integer calculateTotalAmount(int count, int price) {
        return count * price;
    }
}
