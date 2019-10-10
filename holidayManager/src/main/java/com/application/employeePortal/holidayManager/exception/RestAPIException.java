package com.application.employeePortal.holidayManager.exception;

public class RestAPIException extends RuntimeException{

    private static final long serialVersionUID = -8598641340053816150L;

    public RestAPIException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RestAPIException(final String message) {
        super(message);
    }

    public RestAPIException(final Throwable cause) {
        super(cause);
    }
}