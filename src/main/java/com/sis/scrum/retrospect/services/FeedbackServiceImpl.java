package com.sis.scrum.retrospect.services;

import com.sis.scrum.retrospect.models.FeedbackItem;
import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateFeedbackItem;
import com.sis.scrum.retrospect.models.dtos.UpdateFeedbackItem;
import com.sis.scrum.retrospect.repositories.FeedbackItemRepository;
import com.sis.scrum.retrospect.repositories.RetrospectiveRepository;
import com.sis.scrum.retrospect.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

/**
 * The type Feedback service.
 */
@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {
    /**
     * The Feedback item repository.
     */
    @Autowired
    FeedbackItemRepository feedbackItemRepository;
    /**
     * The Retrospective repository.
     */
    @Autowired
    RetrospectiveRepository retrospectiveRepository;
    @Override
    public FeedbackItem createFeedbackItem(CreateFeedbackItem request, Long retrospectiveId) {
        Retrospective retrospective = retrospectiveRepository.findById(retrospectiveId)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND, Constants.RETROSPECTIVE_NOT_FOUND));
        return feedbackItemRepository.save(
                FeedbackItem.builder()
                        .feedbackType(request.getFeedbackType())
                        .body(request.getBody())
                        .name(request.getName())
                        .retrospective(retrospective)
                        .build()
        );
    }

    @Override
    public FeedbackItem updateFeedbackItem(UpdateFeedbackItem request, Long feedbackId) {
        FeedbackItem feedbackItem = feedbackItemRepository.findById(feedbackId)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND, Constants.FEEDBACK_NOT_FOUND));
        feedbackItem.setBody(request.getBody());
        feedbackItem.setFeedbackType(request.getFeedbackType());
        return feedbackItemRepository.save(feedbackItem);
    }
}
