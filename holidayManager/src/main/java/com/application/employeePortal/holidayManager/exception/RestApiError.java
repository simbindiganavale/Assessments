package com.application.employeePortal.holidayManager.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class RestApiError   {

private HttpStatus status;
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
private LocalDateTime timestamp;
private String message;
private String debugMessage;
private List<String> errors;
//private List<ApiSubError> subErrors;

private RestApiError() {
    timestamp = LocalDateTime.now();
}

public RestApiError(HttpStatus status) {
    this();
    this.status = status;
}

public RestApiError(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
}

public RestApiError(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
}

public RestApiError(final HttpStatus status, final String message, final List<String> errors) {
    this();
    this.status = status;
    this.message = message;
    this.errors = errors;
}

public RestApiError(final HttpStatus status, final String message, final String error) {
    this();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
}
}