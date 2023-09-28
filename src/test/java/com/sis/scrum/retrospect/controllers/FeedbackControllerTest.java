package com.sis.scrum.retrospect.controllers;

import com.sis.scrum.retrospect.hateoas.web.ResourceCreatedEvent;
import com.sis.scrum.retrospect.models.FeedbackItem;
import com.sis.scrum.retrospect.models.dtos.CreateFeedbackItem;
import com.sis.scrum.retrospect.models.dtos.UpdateFeedbackItem;
import com.sis.scrum.retrospect.services.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;


import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackControllerTest {

    @InjectMocks
    private FeedbackController feedbackController;

    @Mock
    private FeedbackService feedbackService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void testCreateFeedbackItem() throws Exception {
        // Mock data
        Long retrospectiveId = 1L;
        CreateFeedbackItem request = new CreateFeedbackItem();
        request.setBody("Sample feedback text");

        FeedbackItem mockFeedbackItem = new FeedbackItem();
        mockFeedbackItem.setId(1L);
        mockFeedbackItem.setBody("Sample feedback text");

        // Mock service method
        when(feedbackService.createFeedbackItem(request, retrospectiveId)).thenReturn(mockFeedbackItem);

        // Mock HTTP response
        HttpServletResponse response = new MockHttpServletResponse();

        // Perform POST request
        ResponseEntity<FeedbackItem> responseEntity = feedbackController.create(retrospectiveId, request, response);

        // Verify that the service method was called
        verify(feedbackService, times(1)).createFeedbackItem(request, retrospectiveId);

        // Verify that the event was published
        verify(eventPublisher, times(1)).publishEvent(ArgumentMatchers.any(ResourceCreatedEvent.class));

        // Assert the response status code and body
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockFeedbackItem, responseEntity.getBody());
    }

    @Test
    public void testUpdateFeedbackItem() throws Exception {
        // Mock data
        Long feedbackId = 1L;
        UpdateFeedbackItem request = new UpdateFeedbackItem();
        request.setBody("Updated feedback text");

        FeedbackItem mockFeedbackItem = new FeedbackItem();
        mockFeedbackItem.setId(1L);
        mockFeedbackItem.setBody("Updated feedback text");

        // Mock service method
        when(feedbackService.updateFeedbackItem(request, feedbackId)).thenReturn(mockFeedbackItem);

        // Mock HTTP response
        HttpServletResponse response = new MockHttpServletResponse();

        // Perform PUT request
        ResponseEntity<FeedbackItem> responseEntity = feedbackController.update(feedbackId, request, response);

        // Verify that the service method was called
        verify(feedbackService, times(1)).updateFeedbackItem(request, feedbackId);

        // Verify that the event was published
        verify(eventPublisher, times(1)).publishEvent(ArgumentMatchers.any(ResourceCreatedEvent.class));

        // Assert the response status code and body
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockFeedbackItem, responseEntity.getBody());
    }
}
