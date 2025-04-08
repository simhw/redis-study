package com.moduledomain.command.domain.reservation.exception;


import com.modulecommon.exception.CoreException;

public class ReservationException extends CoreException {
    private ReservationErrorType type;
    private Object payload;

    public ReservationException(ReservationErrorType type) {
        super(type);
    }

    public ReservationException(ReservationErrorType type, Object payload) {
        super(type, payload);
    }

    @Override
    public ReservationErrorType getType() {
        return (ReservationErrorType) super.getType();
    }
}
