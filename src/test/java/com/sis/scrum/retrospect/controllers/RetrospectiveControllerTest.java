package com.sis.scrum.retrospect.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.scrum.retrospect.controllers.RetrospectiveController;
import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateRetrospective;
import com.sis.scrum.retrospect.services.RetrospectiveService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RetrospectiveControllerTest {

    private RetrospectiveController retrospectiveController;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private RetrospectiveService retrospectiveService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        retrospectiveService = mock(RetrospectiveService.class); // Initialize the service mock
        retrospectiveController = new RetrospectiveController(eventPublisher, retrospectiveService);
    }

    @Test
    public void testCreateRetrospective() throws Exception {
        // Mock data
        CreateRetrospective request = new CreateRetrospective();
        request.setName("Sample Retrospective");

        Retrospective mockRetrospective = new Retrospective();
        mockRetrospective.setId(1L);
        mockRetrospective.setName("Sample Retrospective");

        // Mock service method
        when(retrospectiveService.createRetrospective(request)).thenReturn(mockRetrospective);

        // Mock HTTP response
        HttpServletResponse response = new MockHttpServletResponse();

        // Perform POST request
        ResponseEntity<Retrospective> responseEntity = retrospectiveController.create(request, response);

        // Verify that the service method was called
        verify(retrospectiveService, times(1)).createRetrospective(request);

        // Verify that the event was published
        verify(eventPublisher, times(1)).publishEvent(any());

        // Assert the response status code and body
        assertEquals(org.springframework.http.HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockRetrospective, responseEntity.getBody());
    }

    @Test
    public void testGetRetrospectives() throws Exception {
        // Mock data
        int page = 0;
        int size = 5;
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("totalPages", 2);

        // Mock service method
        when(retrospectiveService.findRetrospectives(page, size)).thenReturn(mockResponse);

        // Mock HTTP response
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        HttpServletResponse response = new MockHttpServletResponse();

        // Perform GET request
        ResponseEntity<Map<String, Object>> responseEntity = retrospectiveController.getRetrospectives(page, size, uriBuilder, response);

        // Verify that the service method was called
        verify(retrospectiveService, times(1)).findRetrospectives(page, size);

        // Verify that the event was published
        verify(eventPublisher, times(1)).publishEvent(any());

        // Assert the response status code and body
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    public void testGetRetrospectivesByDate() throws Exception {
        // Mock data
        Date date = new Date();
        int page = 0;
        int size = 5;
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("totalPages", 2);

        // Mock service method
        when(retrospectiveService.findRetrospectivesByDate(page, size, date)).thenReturn(mockResponse);

        // Mock HTTP response
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        HttpServletResponse response = new MockHttpServletResponse();

        // Perform GET request
        ResponseEntity<Map<String, Object>> responseEntity = retrospectiveController.getRetrospectivesByDate(date, page, size, uriBuilder, response);

        // Verify that the service method was called
        verify(retrospectiveService, times(1)).findRetrospectivesByDate(page, size, date);

        // Verify that the event was published
        verify(eventPublisher, times(1)).publishEvent(any());

        // Assert the response status code and body
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }
}

