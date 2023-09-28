package com.sis.scrum.retrospect.services;

import com.sis.scrum.retrospect.models.FeedbackItem;
import com.sis.scrum.retrospect.models.FeedbackItemType;
import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateFeedbackItem;
import com.sis.scrum.retrospect.models.dtos.UpdateFeedbackItem;
import com.sis.scrum.retrospect.repositories.FeedbackItemRepository;
import com.sis.scrum.retrospect.repositories.RetrospectiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class FeedbackServiceImplTest {

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Mock
    private FeedbackItemRepository feedbackItemRepository;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFeedbackItem() {
        // Arrange
        CreateFeedbackItem request = new CreateFeedbackItem();
        request.setFeedbackType(FeedbackItemType.Idea);
        request.setBody("Body");
        request.setName("Name");
        Long retrospectiveId = 1L;

        Retrospective retrospective = new Retrospective();
        retrospective.setId(1L);

        when(retrospectiveRepository.findById(retrospectiveId)).thenReturn(Optional.of(retrospective));
        when(feedbackItemRepository.save(any(FeedbackItem.class))).thenAnswer(invocation -> {
            FeedbackItem savedFeedbackItem = invocation.getArgument(0);
            savedFeedbackItem.setId(1L); // Simulate ID generation
            return savedFeedbackItem;
        });

        // Act
        FeedbackItem createdFeedbackItem = feedbackService.createFeedbackItem(request, retrospectiveId);

        // Assert
        assertNotNull(createdFeedbackItem);
        assertEquals(FeedbackItemType.Idea, createdFeedbackItem.getFeedbackType());
        assertEquals("Body", createdFeedbackItem.getBody());
        assertEquals("Name", createdFeedbackItem.getName());
        assertEquals(retrospective, createdFeedbackItem.getRetrospective());
    }

    @Test
    public void testUpdateFeedbackItem() {
        // Arrange
        UpdateFeedbackItem request = new UpdateFeedbackItem();
        request.setFeedbackType(FeedbackItemType.Idea);
        request.setBody("Updated Body");
        Long feedbackId = 1L;

        FeedbackItem existingFeedbackItem = new FeedbackItem();
        existingFeedbackItem.setId(feedbackId);
        existingFeedbackItem.setFeedbackType(FeedbackItemType.Idea);
        existingFeedbackItem.setBody("Body");

        when(feedbackItemRepository.findById(feedbackId)).thenReturn(Optional.of(existingFeedbackItem));
        when(feedbackItemRepository.save(any(FeedbackItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        FeedbackItem updatedFeedbackItem = feedbackService.updateFeedbackItem(request, feedbackId);

        // Assert
        assertNotNull(updatedFeedbackItem);
        assertEquals(FeedbackItemType.Idea, updatedFeedbackItem.getFeedbackType());
        assertEquals("Updated Body", updatedFeedbackItem.getBody());
        assertEquals(feedbackId, updatedFeedbackItem.getId());
    }

    @Test
    public void testCreateFeedbackItemRetrospectiveNotFound() {
        // Arrange
        CreateFeedbackItem request = new CreateFeedbackItem();
        Long retrospectiveId = 1L;

        when(retrospectiveRepository.findById(retrospectiveId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(HttpServerErrorException.class, () -> {
            feedbackService.createFeedbackItem(request, retrospectiveId);
        });
    }

    @Test
    public void testUpdateFeedbackItemFeedbackItemNotFound() {
        // Arrange
        UpdateFeedbackItem request = new UpdateFeedbackItem();
        Long feedbackId = 1L;

        when(feedbackItemRepository.findById(feedbackId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(HttpServerErrorException.class, () -> {
            feedbackService.updateFeedbackItem(request, feedbackId);
        });
    }
}
