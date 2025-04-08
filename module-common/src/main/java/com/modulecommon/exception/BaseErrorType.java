package com.modulecommon.exception;

import org.springframework.boot.logging.LogLevel;

public interface BaseErrorType {
    ErrorCode getCode();

    String getMessage();

    LogLevel getLogLevel();
}
