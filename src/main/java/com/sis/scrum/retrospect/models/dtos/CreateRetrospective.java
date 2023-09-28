package com.sis.scrum.retrospect.models.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Create retrospective.
 */
@Data
public class CreateRetrospective {
    @NotBlank(message = "name cannot be null")
    @Size(min = 3, message = "Name cannot be less than 3 characters")
    @Column(unique = true)
    private String name;
    private String summary;
    @NotNull(message = "Date cannot be null")
    @FutureOrPresent(message = "Date should be in the present or future")
    private Date entryDate;
    @NotEmpty(message = "Participants list cannot be empty")
    @Size(min = 1, message = "Participants list must not be empty")
    private List<String> participants = new ArrayList<>();
}
