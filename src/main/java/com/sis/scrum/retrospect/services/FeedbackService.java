package com.sis.scrum.retrospect.services;

import com.sis.scrum.retrospect.models.FeedbackItem;
import com.sis.scrum.retrospect.models.dtos.CreateFeedbackItem;
import com.sis.scrum.retrospect.models.dtos.UpdateFeedbackItem;

/**
 * The interface Feedback service.
 */
public interface FeedbackService {
    /**
     * Create feedback item feedback item.
     *
     * @param request         the request
     * @param retrospectiveId the retrospective id
     * @return the feedback item
     */
    FeedbackItem createFeedbackItem(CreateFeedbackItem request, Long retrospectiveId);

    /**
     * Update feedback item feedback item.
     *
     * @param request    the request
     * @param feedbackId the feedback id
     * @return the feedback item
     */
    FeedbackItem updateFeedbackItem(UpdateFeedbackItem request, Long feedbackId);
}
