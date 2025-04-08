package com.moduleapi.exception;

import com.modulecommon.exception.CoreException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String code;
    private String message;
    private Object data;

    public static ErrorResponse of(CoreException e) {
        return new ErrorResponseBuilder()
                .code(e.getType().getCode().name())
                .message(e.getType().getMessage())
                .data(e.getPayload())
                .build();
    }
}
