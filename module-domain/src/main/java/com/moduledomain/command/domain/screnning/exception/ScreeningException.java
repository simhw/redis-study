package com.moduledomain.command.domain.screnning.exception;

import com.modulecommon.exception.CoreException;

public class ScreeningException extends CoreException {
    private ScreeningErrorType type;
    private Object payload;

    public ScreeningException(ScreeningErrorType type) {
        super(type);
    }

    public ScreeningException(ScreeningErrorType type, Object payload) {
        super(type, payload);
    }

    @Override
    public ScreeningErrorType getType() {
        return (ScreeningErrorType) super.getType();
    }
}
