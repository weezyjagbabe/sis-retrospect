package com.sis.scrum.retrospect.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * The type Retrospective.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Retrospective extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String summary;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE) // Use TemporalType.TIMESTAMP for date and time
    private Date entryDate;

    @ElementCollection
    private List<String> participants;

    @OneToMany(mappedBy = "retrospective", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackItem> feedbackItems = new ArrayList<>();

    @Override
    public String toString() {
        return "Retrospective{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", entryDate=" + entryDate +
                ", participants=" + participants +
                ", feedbackItems=" + feedbackItems +
                '}';
    }
}

