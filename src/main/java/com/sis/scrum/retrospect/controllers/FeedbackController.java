package com.sis.scrum.retrospect.controllers;

import com.sis.scrum.retrospect.hateoas.web.ResourceCreatedEvent;
import com.sis.scrum.retrospect.models.FeedbackItem;
import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateFeedbackItem;
import com.sis.scrum.retrospect.models.dtos.CreateRetrospective;
import com.sis.scrum.retrospect.models.dtos.UpdateFeedbackItem;
import com.sis.scrum.retrospect.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * The type Feedback controller.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class FeedbackController extends BaseController {
    @Autowired
    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    private final FeedbackService feedbackService;

    /**
     * Create response entity.
     *
     * @param retrospectiveId the retrospective id
     * @param request         the request
     * @param response        the response
     * @return the response entity
     */
    @Operation(summary = "Create a feedback for a retrospective.")
    @PostMapping(value = "/retrospective/{retrospectiveId}/feedback-item",
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FeedbackItem> create(@PathVariable final Long retrospectiveId,
                                        @RequestBody @Valid final CreateFeedbackItem request,
                                        final HttpServletResponse response) {
        FeedbackItem feedbackItem = feedbackService.createFeedbackItem(request, retrospectiveId);
        final String idOfCreatedResource = String.valueOf(feedbackItem.getId());

        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackItem);
    }

    /**
     * Update response entity.
     *
     * @param feedbackId the feedback id
     * @param request    the request
     * @param response   the response
     * @return the response entity
     */
    @Operation(summary = "Update a feedback.")
    @PutMapping(value = "/feedback-item/{feedbackId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FeedbackItem> update(@PathVariable final Long feedbackId,
                                        @RequestBody @Valid final UpdateFeedbackItem request,
                                        final HttpServletResponse response) {
        FeedbackItem feedbackItem = feedbackService.updateFeedbackItem(request,feedbackId);
        final String idOfCreatedResource = String.valueOf(feedbackItem.getId());

        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackItem);
    }
}
