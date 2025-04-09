package com.moduleapi.controller;

import com.moduleapi.dto.CreateReservationDto;
import com.modulecommon.annotation.RateLimit;
import com.moduledomain.command.service.ReservationCommand;
import com.moduledomain.command.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @RateLimit(
            key = "'reserve:' + #userId + ':' + #request.screeningId",
            limitCount = 1,
            limitTimeSecond = 300 // 5 * 60 = 5분
    )
    @PostMapping
    public String create(
            @RequestHeader Long userId,
            @RequestBody @Valid CreateReservationDto.Request request
    ) {
        ReservationCommand command = new ReservationCommand(
                userId,
                request.getScreeningId(),
                request.getAllocatedSeatIds()
        );
        reservationService.reserve(command);
        return "ok";
    }
}
