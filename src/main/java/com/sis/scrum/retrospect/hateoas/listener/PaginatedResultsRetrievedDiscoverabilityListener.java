package com.sis.scrum.retrospect.hateoas.listener;

import com.sis.scrum.retrospect.hateoas.web.PaginatedResultsRetrievedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Paginated results retrieved event discoverability listener.
 */
@Component
class PaginatedResultsRetrievedEventDiscoverabilityListener implements ApplicationListener<PaginatedResultsRetrievedEvent> {

    @Override
    public void onApplicationEvent(final PaginatedResultsRetrievedEvent ev) {
        addLinkHeaderOnPagedResourceRetrieval(ev.getUriBuilder(), ev.getResponse(), ev.getClazz(), ev.getPage(), ev.getTotalPages(), ev.getPageSize());
    }

    /**
     * Add link header on paged resource retrieval.
     *
     * @param uriBuilder the uri builder
     * @param response   the response
     * @param clazz      the clazz
     * @param page       the page
     * @param totalPages the total pages
     * @param pageSize   the page size
     */
    void addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final Class clazz, final int page, final int totalPages, final int pageSize) {
        final String resourceName = clazz.getSimpleName().toLowerCase();
        uriBuilder.path("/api/v1/" + resourceName);

        final StringBuilder linkHeader = new StringBuilder();
        if (hasNextPage(page, totalPages)) {
            final String uriForNextPage = constructNextPageUri(uriBuilder, page, pageSize);
            linkHeader.append(createLinkHeader(uriForNextPage, "next"));
        }
        if (hasPreviousPage(page)) {
            final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForPrevPage, "prev"));
        }
        if (hasFirstPage(page)) {
            final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForFirstPage, "first"));
        }
        if (hasLastPage(page, totalPages)) {
            final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForLastPage, "last"));
        }
        response.addHeader("Link", linkHeader.toString());
    }

    /**
     * Construct next page uri string.
     *
     * @param uriBuilder the uri builder
     * @param page       the page
     * @param size       the size
     * @return the string
     */
    String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam("page", page + 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    /**
     * Construct prev page uri string.
     *
     * @param uriBuilder the uri builder
     * @param page       the page
     * @param size       the size
     * @return the string
     */
    String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam("page", page - 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    /**
     * Construct first page uri string.
     *
     * @param uriBuilder the uri builder
     * @param size       the size
     * @return the string
     */
    String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
        return uriBuilder.replaceQueryParam("page", 0).replaceQueryParam("size", size).build().encode().toUriString();
    }

    /**
     * Construct last page uri string.
     *
     * @param uriBuilder the uri builder
     * @param totalPages the total pages
     * @param size       the size
     * @return the string
     */
    String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int size) {
        return uriBuilder.replaceQueryParam("page", totalPages).replaceQueryParam("size", size).build().encode().toUriString();
    }

    /**
     * Has next page boolean.
     *
     * @param page       the page
     * @param totalPages the total pages
     * @return the boolean
     */
    boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1;
    }

    /**
     * Has previous page boolean.
     *
     * @param page the page
     * @return the boolean
     */
    boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    /**
     * Has first page boolean.
     *
     * @param page the page
     * @return the boolean
     */
    boolean hasFirstPage(final int page) {
        return hasPreviousPage(page);
    }

    /**
     * Has last page boolean.
     *
     * @param page       the page
     * @param totalPages the total pages
     * @return the boolean
     */
    boolean hasLastPage(final int page, final int totalPages) {
        return totalPages > 1 && hasNextPage(page, totalPages);
    }

    /**
     * Append comma if necessary.
     *
     * @param linkHeader the link header
     */
    void appendCommaIfNecessary(final StringBuilder linkHeader) {
        if (linkHeader.length() > 0) {
            linkHeader.append(", ");
        }
    }

    /**
     * Create link header string.
     *
     * @param uri the uri
     * @param rel the rel
     * @return the string
     */
    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

}
