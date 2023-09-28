package com.sis.scrum.retrospect.controllers;

import com.sis.scrum.retrospect.models.exceptions.AppError;
import com.sis.scrum.retrospect.models.exceptions.AppException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BaseControllerTest {

    @InjectMocks
    private BaseController baseController;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testHandleValidationExceptions() {
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("fieldName1", "error1"));
        errors.add(new ObjectError("fieldName2", "error2"));

        when(bindingResult.getAllErrors()).thenReturn(errors);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<Object> responseEntity = baseController.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");

        ResponseEntity<AppError> responseEntity = baseController.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleAppException() {
        AppException ex = new AppException("App exception message");

        ResponseEntity<AppError> responseEntity = baseController.handleAppException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testHttpServerErrorException() {
        HttpServerErrorException ex = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");

        ResponseEntity<AppError> responseEntity = baseController.httpServerErrorException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
