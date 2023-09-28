package com.sis.scrum.retrospect.models.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The enum Api exceptions.
 */
public enum ApiExceptions {

    /**
     * The Resource not found.
     */
    RESOURCE_NOT_FOUND("Resource not found") {
        @Override
        public HttpStatus getStatus() {
            return HttpStatus.NOT_FOUND;
        }
    },
    /**
     * The Resource exists.
     */
    RESOURCE_EXISTS("Resource exists");

    private final String message;

    ApiExceptions(final String errorMessage) {
        this.message = errorMessage;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
