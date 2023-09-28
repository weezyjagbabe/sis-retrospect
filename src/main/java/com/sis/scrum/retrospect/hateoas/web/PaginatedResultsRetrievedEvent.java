package com.sis.scrum.retrospect.hateoas.web;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * The type Paginated results retrieved event.
 *
 * @param <T> the type parameter
 */
public final class PaginatedResultsRetrievedEvent<T extends Serializable> extends ApplicationEvent {
    private final UriComponentsBuilder uriBuilder;
    private final HttpServletResponse response;
    private final int page;
    private final int totalPages;
    private final int pageSize;

    /**
     * Instantiates a new Paginated results retrieved event.
     *
     * @param clazz           the clazz
     * @param uriBuilderToSet the uri builder to set
     * @param responseToSet   the response to set
     * @param pageToSet       the page to set
     * @param totalPagesToSet the total pages to set
     * @param pageSizeToSet   the page size to set
     */
    public PaginatedResultsRetrievedEvent(
            final Class<T> clazz,
            final UriComponentsBuilder uriBuilderToSet,
            final HttpServletResponse responseToSet,
            final int pageToSet,
            final int totalPagesToSet,
            final int pageSizeToSet) {
        super(clazz);

        uriBuilder = uriBuilderToSet;
        response = responseToSet;
        page = pageToSet;
        totalPages = totalPagesToSet;
        pageSize = pageSizeToSet;
    }

    // API

    /**
     * Gets uri builder.
     *
     * @return the uri builder
     */
    public UriComponentsBuilder getUriBuilder() {
        return uriBuilder;
    }

    /**
     * Gets response.
     *
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Gets total pages.
     *
     * @return the total pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        return (Class<T>) getSource();
    }

}