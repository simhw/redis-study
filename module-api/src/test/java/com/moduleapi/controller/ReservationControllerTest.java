package com.moduleapi.controller;

import com.moduleapi.dto.CreateReservationDto;
import com.moduledomain.command.domain.reservation.Reservation;
import com.moduledomain.command.domain.screnning.*;

import com.moduleinfra.repository.reservation.ReservationRepositoryImpl;
import com.moduleinfra.repository.screening.ScreeningRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = "/data/test-setup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data/test-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ReservationControllerTest {

    @Autowired
    ReservationController reservationController;

    @Autowired
    ReservationRepositoryImpl reservationRepository;

    @Autowired
    ScreeningRepositoryImpl screeningRepository;

    @Test
    void 영화_예매_성공() {
        // given
        Long userId = 1L;
        List<Long> allocatedSeatIds = List.of(1L, 2L, 3L, 4L, 5L);
        CreateReservationDto.Request request = new CreateReservationDto.Request(1L, allocatedSeatIds);

        // when
        reservationController.create(userId, request);

        // then
        Reservation reservation = reservationRepository.getReservationBy(1L);
        Assertions.assertThat(reservation).isNotNull();

        List<AllocatedSeat> allocatedSeats = screeningRepository.getAllocatedSeatsBy(allocatedSeatIds);
        allocatedSeats.forEach(allocatedSeat -> {
            Assertions.assertThat(allocatedSeat.isReserved()).isTrue();
        });
    }

    @Test
    @DisplayName("회원 50명이 동시에 같은 좌석을 예매하는 경우 하나의 예약만 생성된다")
    void 영화_예매_동시성_테스트() throws InterruptedException {
        int THREAD_COUNT = 50;

        // given
        ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        AtomicInteger success = new AtomicInteger(0);

        List<Long> allocatedSeatIds = List.of(1L, 2L, 3L, 4L, 5L);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (long i = 1; i <= THREAD_COUNT; i++) {
            Long userId = i;

            es.submit(() -> {
                try {
                    CreateReservationDto.Request request = new CreateReservationDto.Request(1L, allocatedSeatIds);
                    reservationController.create(userId, request);
                    success.incrementAndGet();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        es.shutdown();
        stopWatch.stop();

        System.out.println("소요시간: " + stopWatch.getTotalTimeMillis() + "ms");
        System.out.println(stopWatch.prettyPrint());

        List<Reservation> reservations = reservationRepository.getReservationsByScreeningId(1L);
        Assertions.assertThat(reservations).isNotNull();
        Assertions.assertThat(reservations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("회원이 같은 상영을 중복 예약하는 경우 TooManyRequests 예외를 반환한다.")
    void 영화_예매_중복_요청() throws InterruptedException {
        Long userId = 1L;
        List<Long> allocatedSeatIds1 = List.of(1L, 2L, 3L);
        CreateReservationDto.Request request1 = new CreateReservationDto.Request(1L, allocatedSeatIds1);

        List<Long> allocatedSeatIds2 = List.of(4L, 5L);
        CreateReservationDto.Request request2 = new CreateReservationDto.Request(1L, allocatedSeatIds2);

        // when, then
        reservationController.create(userId, request1);
        assertThrows(IllegalArgumentException.class, () -> {
            reservationController.create(userId, request2);
        });
    }
}