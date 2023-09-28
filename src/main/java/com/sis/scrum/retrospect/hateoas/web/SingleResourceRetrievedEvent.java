package com.sis.scrum.retrospect.hateoas.web;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Single resource retrieved event.
 */
public final class SingleResourceRetrievedEvent extends ApplicationEvent {
    private final HttpServletResponse response;

    /**
     * Instantiates a new Single resource retrieved event.
     *
     * @param source   the source
     * @param response the response
     */
    public SingleResourceRetrievedEvent(final Object source, final HttpServletResponse response) {
        super(source);

        this.response = response;
    }

    // API

    /**
     * Gets response.
     *
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

}
