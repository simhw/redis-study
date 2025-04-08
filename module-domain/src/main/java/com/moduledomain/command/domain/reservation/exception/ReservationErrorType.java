package com.moduledomain.command.domain.reservation.exception;

import com.modulecommon.exception.BaseErrorType;
import com.modulecommon.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
@AllArgsConstructor
public enum ReservationErrorType implements BaseErrorType {
    RESERVATION_NOT_FOUND(ErrorCode.RESOURCE_NOT_FOUND, "예약을 찾을 수 없습니다.", LogLevel.WARN),
    RESERVED_SEAT_NOT_FOUND(ErrorCode.RESOURCE_NOT_FOUND, "예약된 좌석이 존재하지 않습니다.", LogLevel.WARN),
    ALREADY_RESERVED_SEAT(ErrorCode.CONFLICT, "이미 예약된 좌석입니다.", LogLevel.WARN),
    NOT_ADJACENT_SEATS(ErrorCode.VALIDATION_ERROR, "연속된 좌석이 아닙니다.", LogLevel.INFO);

    private final ErrorCode code;
    private final String message;
    private final LogLevel logLevel;
}
