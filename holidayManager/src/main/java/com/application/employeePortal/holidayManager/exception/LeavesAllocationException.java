package com.application.employeePortal.holidayManager.exception;

public class LeavesAllocationException extends RuntimeException{

    private static final long serialVersionUID = -8598641340053816150L;

    public LeavesAllocationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LeavesAllocationException(final String message) {
        super(message);
    }

    public LeavesAllocationException(final Throwable cause) {
        super(cause);
    }
 
    
}