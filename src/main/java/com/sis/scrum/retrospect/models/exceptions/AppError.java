package com.sis.scrum.retrospect.models.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


/**
 * The type App error.
 */
@AllArgsConstructor
@Getter
public class AppError {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}
