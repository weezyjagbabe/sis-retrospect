package com.sis.scrum.retrospect.hateoas.listener;
/**
 * @author Solomon Bortey on 22/09/2023 <borteys@yahoo.com>
 * @project retrospect
 */
import com.sis.scrum.retrospect.hateoas.web.ResourceCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;


/**
 * The type Resource created discoverability listener.
 */
@Component
class ResourceCreatedDiscoverabilityListener implements ApplicationListener<ResourceCreatedEvent> {

    @Override
    public void onApplicationEvent(final ResourceCreatedEvent resourceCreatedEvent) {

        final HttpServletResponse response = resourceCreatedEvent.getResponse();
        final String idOfNewResource = resourceCreatedEvent.getIdOfNewResource();

        addLinkHeaderOnResourceCreation(response, idOfNewResource);
    }

    /**
     * Add link header on resource creation.
     *
     * @param response        the response
     * @param idOfNewResource the id of new resource
     */
    void addLinkHeaderOnResourceCreation(final HttpServletResponse response, final String idOfNewResource) {
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idOfNewResource}").buildAndExpand(idOfNewResource).toUri();
        response.setHeader(HttpHeaders.LOCATION, uri.toASCIIString());
    }

}