package com.moduledomain.command.domain.user.excepiton;

import com.modulecommon.exception.BaseErrorType;
import com.modulecommon.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
@AllArgsConstructor
public enum UserErrorType implements BaseErrorType {
    USER_NOT_FOUND(ErrorCode.RESOURCE_NOT_FOUND, "존재하지 않는 사용자입니다.", LogLevel.WARN),
    INVALID_USER(ErrorCode.CONFLICT, "유효하지 않은 사용자입니다.", LogLevel.WARN);

    private final ErrorCode code;
    private final String message;
    private final LogLevel logLevel;
}
