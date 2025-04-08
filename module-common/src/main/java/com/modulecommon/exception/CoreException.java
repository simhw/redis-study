package com.modulecommon.exception;

import lombok.Getter;

@Getter
public abstract class CoreException extends RuntimeException {
    private final BaseErrorType type;
    private final Object payload;

    protected CoreException(BaseErrorType type) {
        super(type.toString());
        this.type = type;
        this.payload = null;
    }

    protected CoreException(BaseErrorType type, Object payload) {
        super(type.toString());
        this.type = type;
        this.payload = payload;
    }
}
