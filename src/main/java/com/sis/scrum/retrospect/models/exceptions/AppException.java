package com.sis.scrum.retrospect.models.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * The type App exception.
 */
@Getter
@Setter
public class AppException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    /**
     * Instantiates a new App exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AppException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new App exception.
     *
     * @param message the message
     */
    public AppException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new App exception.
     *
     * @param exception the exception
     */
    public AppException(final ApiExceptions exception) {
        super(exception.getMessage());
        this.httpStatus = exception.getStatus();
    }

    /**
     * Instantiates a new App exception.
     *
     * @param message the message
     * @param status  the status
     */
    public AppException(final String message, final HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
