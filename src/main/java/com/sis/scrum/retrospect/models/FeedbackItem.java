package com.sis.scrum.retrospect.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The type Feedback item.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String body;
    @Enumerated(EnumType.STRING)
    private FeedbackItemType feedbackType;

    @JsonIgnore
    @ManyToOne
    private Retrospective retrospective;

    @Override
    public String toString() {
        return "FeedbackItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", feedbackType=" + feedbackType +
                ", retrospective=" + retrospective +
                '}';
    }
}
