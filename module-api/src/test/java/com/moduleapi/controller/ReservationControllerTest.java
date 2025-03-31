package com.moduleapi.controller;

import com.moduleapi.dto.CreateReservationDto;
import com.moduledomain.command.domain.reservation.Reservation;
import com.moduledomain.command.domain.screnning.*;

import com.moduleinfra.repository.reservation.ReservationRepositoryImpl;
import com.moduleinfra.repository.screening.ScreeningRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
    void create() {
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
}