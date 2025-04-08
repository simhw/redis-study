package com.moduledomain.command.service;

import com.modulecommon.support.IDistributedLock;
import com.moduledomain.command.domain.reservation.Reservation;
import com.moduledomain.command.domain.reservation.ReservationRepository;

import com.moduledomain.command.domain.reservation.ReservedEvent;
import com.moduledomain.command.domain.reservation.ReservedSeat;
import com.moduledomain.command.domain.reservation.exception.ReservationErrorType;
import com.moduledomain.command.domain.reservation.exception.ReservationException;
import com.moduledomain.command.domain.screnning.AllocatedSeat;
import com.moduledomain.command.domain.screnning.Screening;
import com.moduledomain.command.domain.screnning.ScreeningRepository;
import com.moduledomain.command.domain.theater.Seat;
import com.moduledomain.command.domain.theater.SeatNo;
import com.moduledomain.command.domain.theater.SeatRepository;
import com.moduledomain.command.domain.user.User;
import com.moduledomain.command.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * theater(seat)
 * ⬆️
 * screening(allocated_seat)
 * ⬆️
 * reservation(reserved_seat)
 */

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;

    private final ApplicationEventPublisher eventPublisher;
    private final IDistributedLock iDistributedLock;
    private final TransactionTemplate transactionTemplate;

    public void reserve(ReservationCommand command) {
        // 아직 시작하지 않은 상영인지 확인
        Screening screening = screeningRepository.getScreeningBy(command.getScreeningId());
        screening.verifyIsNotYetStart();

        // 존재하는 회원인지 확인
        User user = userRepository.getUserBy(command.getUserId());
        user.validateActiveUser();

        // 예약 객체 생성
        Reservation reservation = new Reservation(
                command.getUserId(),
                command.getScreeningId(),
                command.getAllocatedSeatIds().stream()
                        .map(ReservedSeat::new)
                        .toList(),
                screening.getPrice()
        );

        String[] keys = command.getAllocatedSeatIds().stream()
                .map(String::valueOf)
                .toArray(String[]::new);

        // 분산락 획득
        iDistributedLock.lockAndExecute(keys, 2, 3, TimeUnit.SECONDS, () -> {
            // 임계 영역 및 트랜잭션 시작
            transactionTemplate.execute(status -> {
                // 예약되지 않은 좌석인지 확인
                List<AllocatedSeat> allocatedSeats = screeningRepository.getAllocatedSeatsBy(command.getAllocatedSeatIds());
                validateIsNotYetReservedSeat(allocatedSeats);

                // 연속된 좌석 형태인지 확인
                List<Long> seatIds = allocatedSeats.stream()
                        .map(AllocatedSeat::getSeatId)
                        .toList();
                List<Seat> seats = seatRepository.getAllSeatBy(seatIds);
                validateSeatsNextToEachOther(seats);

                // AllocatedSeat의 상태를 예약됨으로 변경
                allocatedSeats.forEach(AllocatedSeat::reserve);
                screeningRepository.saveAllocatedSeats(allocatedSeats);
                Long id = reservationRepository.saveReservation(reservation);

                // 예약 완료 메시지 전송 이벤트 발행
                eventPublisher.publishEvent(new ReservedEvent(id));
                return id;
            });

            return null;
        });
    }

    /**
     * 예약된 좌석인지 확인
     */
    private void validateIsNotYetReservedSeat(List<AllocatedSeat> allocatedSeats) {
        boolean reserved = allocatedSeats.stream()
                .anyMatch(AllocatedSeat::isReserved);
        if (reserved) {
            throw new ReservationException(ReservationErrorType.ALREADY_RESERVED_SEAT);
        }
    }

    /**
     * 연속된 좌석인지 확인
     */
    private void validateSeatsNextToEachOther(List<Seat> seats) {
        if (seats.size() < 2) return;

        List<SeatNo> sortedSeatNos = seats.stream()
                .map(Seat::getNo)
                .sorted()
                .toList();

        for (int i = 1; i < sortedSeatNos.size(); i++) {
            if (!sortedSeatNos.get(i - 1).isNextTo(sortedSeatNos.get(i))) {
                throw new ReservationException(ReservationErrorType.NOT_ADJACENT_SEATS);
            }
        }
    }
}
