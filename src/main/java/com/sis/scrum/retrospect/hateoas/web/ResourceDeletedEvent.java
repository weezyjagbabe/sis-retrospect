package com.sis.scrum.retrospect.hateoas.web;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Resource deleted event.
 */
public class ResourceDeletedEvent extends ApplicationEvent {
    private final HttpServletResponse response;
    private final String idOfDeletedResource;

    /**
     * Instantiates a new Resource deleted event.
     *
     * @param source              the source
     * @param response            the response
     * @param idOfDeletedResource the id of deleted resource
     */
    public ResourceDeletedEvent(final Object source, final HttpServletResponse response, final String idOfDeletedResource) {
        super(source);

        this.response = response;
        this.idOfDeletedResource = idOfDeletedResource;
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
     * Gets id of deleted resource.
     *
     * @return the id of deleted resource
     */
    public String getIdOfDeletedResource() {
        return idOfDeletedResource;
    }

}