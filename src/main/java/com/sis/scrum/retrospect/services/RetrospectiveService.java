package com.sis.scrum.retrospect.services;

import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateRetrospective;

import java.util.Date;
import java.util.Map;

/**
 * The interface Retrospective service.
 */
public interface RetrospectiveService {
    /**
     * Create retrospective retrospective.
     *
     * @param request the request
     * @return the retrospective
     */
    Retrospective createRetrospective(CreateRetrospective request);

    /**
     * Find retrospectives map.
     *
     * @param page the page
     * @param size the size
     * @return the map
     */
    Map<String, Object> findRetrospectives(int page, int size);

    /**
     * Find retrospectives by date map.
     *
     * @param page the page
     * @param size the size
     * @param date the date
     * @return the map
     */
    Map<String, Object> findRetrospectivesByDate(int page, int size, Date date);
}
