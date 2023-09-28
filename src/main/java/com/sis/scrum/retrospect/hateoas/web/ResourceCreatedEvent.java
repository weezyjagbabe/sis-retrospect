package com.sis.scrum.retrospect.hateoas.web;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Resource created event.
 */
public class ResourceCreatedEvent extends ApplicationEvent {
    private final HttpServletResponse response;
    private final String idOfNewResource;

    /**
     * Instantiates a new Resource created event.
     *
     * @param source          the source
     * @param response        the response
     * @param idOfNewResource the id of new resource
     */
    public ResourceCreatedEvent(final Object source, final HttpServletResponse response, final String idOfNewResource) {
        super(source);

        this.response = response;
        this.idOfNewResource = idOfNewResource;
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

    /**
     * Gets id of new resource.
     *
     * @return the id of new resource
     */
    public String getIdOfNewResource() {
        return idOfNewResource;
    }

}