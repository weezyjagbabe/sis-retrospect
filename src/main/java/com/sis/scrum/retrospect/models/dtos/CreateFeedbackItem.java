package com.sis.scrum.retrospect.models.dtos;

import com.sis.scrum.retrospect.models.FeedbackItemType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type Create feedback item.
 */
@Data
public class CreateFeedbackItem {
    @NotBlank(message = "name cannot be null")
    @Size(min = 3, message = "name cannot be less than 3 characters")
    private String name;
    @NotBlank(message = "body cannot be null")
    @Size(min = 3, message = "body cannot be less than 3 characters")
    private String body;
    @NotNull(message = "feedbackType cannot be null")
    @Enumerated(EnumType.STRING)
    private FeedbackItemType feedbackType;
}
