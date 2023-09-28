package com.sis.scrum.retrospect.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The enum Feedback item type.
 */
public enum FeedbackItemType {
    /**
     * Positive feedback item type.
     */
    @JsonProperty("Positive")
    Positive("Positive"),
    /**
     * Negative feedback item type.
     */
    @JsonProperty("Negative")
    Negative("Negative"),
    /**
     * Idea feedback item type.
     */
    @JsonProperty("Idea")
    Idea("Idea"),
    /**
     * Praise feedback item type.
     */
    @JsonProperty("Praise")
    Praise("Praise");
    @NotBlank(message = "FeedbackItemType code cannot be blank")
    @Size(min = 4, message = "FeedbackItemType is invalid must be Praise, Positive, Negative or Idea")
    private String code;

    FeedbackItemType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "FeedbackItemType{" +
                "code='" + code + '\'' +
                '}';
    }
}
