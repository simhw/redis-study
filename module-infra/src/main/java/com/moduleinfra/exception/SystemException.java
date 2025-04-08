package com.moduleinfra.exception;

import com.modulecommon.exception.BaseErrorType;
import com.modulecommon.exception.CoreException;

public class SystemException extends CoreException {
    private SystemErrorType type;
    private Object payload;

    public SystemException(BaseErrorType type) {
        super(type);
    }

    public SystemException(BaseErrorType type, Object payload) {
        super(type, payload);
    }
}
