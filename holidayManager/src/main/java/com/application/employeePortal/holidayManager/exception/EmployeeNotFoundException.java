package com.application.employeePortal.holidayManager.exception;

public class EmployeeNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -8598641340053816150L;

    public EmployeeNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(final String message) {
        super(message);
    }

    public EmployeeNotFoundException(final Throwable cause) {
        super(cause);
    }
 
    
}