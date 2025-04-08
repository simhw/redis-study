package com.moduleinfra.exception;

import com.modulecommon.exception.BaseErrorType;
import com.modulecommon.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
@AllArgsConstructor
public enum SystemErrorType implements BaseErrorType {
    TOO_MANY_REQUEST(ErrorCode.TOO_MANY_REQUESTS, "요청 건수를 초과했습니다.", LogLevel.INFO),
    REQUEST_BLOCKED(ErrorCode.TOO_MANY_REQUESTS, "지속적인 요청으로 인해 일시적으로 차단되었습니다.", LogLevel.WARN);

    private ErrorCode code;
    private String message;
    private LogLevel logLevel;
}
