package com.moduleapi.exception;

import com.modulecommon.exception.BaseErrorType;
import com.modulecommon.exception.CoreException;
import com.modulecommon.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CoreExceptionHandler {

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ErrorResponse> handleCoreException(CoreException e) {
        BaseErrorType type = e.getType();
        logging(type);

        ErrorCode code = type.getCode();
        HttpStatus status = switch (code) {
            case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
            case RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case DATABASE_ERROR, REDIS_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
            case TOO_MANY_REQUESTS -> HttpStatus.TOO_MANY_REQUESTS;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, status);
    }

    private void logging(BaseErrorType type) {
        LogLevel level = type.getLogLevel();

        switch (level) {
            case ERROR -> log.error("[{}] {}", type.getCode().name(), type.getMessage());
            case WARN -> log.warn("[{}] {}", type.getCode().name(), type.getMessage());
            default -> log.info("[{}] {}", type.getCode().name(), type.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {
        log.error("[UNEXPECTED_ERROR] {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.INTERNAL_ERROR.name())
                        .message(ex.getMessage())
                        .build());

    }

}