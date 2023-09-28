package com.sis.scrum.retrospect.repositories;

import com.sis.scrum.retrospect.models.FeedbackItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Feedback item repository.
 */
@Repository
public interface FeedbackItemRepository extends JpaRepository<FeedbackItem, Long> {

}
