package com.moduledomain.command.domain.screnning.exception;

import com.modulecommon.exception.BaseErrorType;
import com.modulecommon.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
@AllArgsConstructor
public enum ScreeningErrorType implements BaseErrorType {
    SCREENING_NOT_FOUND(ErrorCode.RESOURCE_NOT_FOUND, "상영 정보를 찾을 수 없습니다.", LogLevel.WARN),
    ALREADY_RESERVED_SCREENING(ErrorCode.VALIDATION_ERROR, "이미 상영이 시작되었습니다.", LogLevel.INFO);

    private final ErrorCode code;
    private final String message;
    private final LogLevel logLevel;
}
