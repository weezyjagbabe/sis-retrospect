package com.sis.scrum.retrospect.repositories;

import com.sis.scrum.retrospect.models.Retrospective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * The interface Retrospective repository.
 */
@Repository
public interface RetrospectiveRepository extends JpaRepository<Retrospective,Long> {
    /**
     * Find by entry date page.
     *
     * @param date the date
     * @param page the page
     * @return the page
     */
    Page<Retrospective> findByEntryDate(Date date, Pageable page);
}
