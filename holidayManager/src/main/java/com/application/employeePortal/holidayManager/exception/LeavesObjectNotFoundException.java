package com.application.employeePortal.holidayManager.exception;

public class LeavesObjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -8598641340053816150L;

    public LeavesObjectNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LeavesObjectNotFoundException(final String message) {
        super(message);
    }

    public LeavesObjectNotFoundException(final Throwable cause) {
        super(cause);
    }
 
    
}