package com.moduledomain.command.domain.user.excepiton;

import com.modulecommon.exception.CoreException;

public class UserException extends CoreException {
    private UserErrorType code;
    private Object payload;

    public UserException(UserErrorType type) {
        super(type);
    }

    public UserException(UserErrorType type, Object payload) {
        super(type, payload);
    }

    @Override
    public UserErrorType getType() {
        return (UserErrorType) super.getType();
    }
}
