package com.sis.scrum.retrospect.controllers;

import com.sis.scrum.retrospect.models.exceptions.AppError;
import com.sis.scrum.retrospect.models.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Base controller.
 */
@Slf4j
@ControllerAdvice
@RequestMapping("/api/v1")
public class BaseController {

    /**
     * Handle validation exceptions response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(
            final MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handle illegal argument exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<AppError> handleIllegalArgumentException(
            final IllegalArgumentException ex) {
        AppError apiException = new AppError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        log.info(ex.getMessage());
        return new ResponseEntity<>(
                apiException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle app exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<AppError> handleAppException(final AppException ex) {
        AppError apiException = new AppError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(
                apiException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Http server error exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = {HttpServerErrorException.class})
    public ResponseEntity<AppError> httpServerErrorException(final HttpServerErrorException ex) {
        AppError apiException = new AppError(
                ex.getStatusText(),
                ex.getStatusCode(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        log.info(ex.getMessage());
        return new ResponseEntity<>(
                apiException, ex.getStatusCode());
    }

}
