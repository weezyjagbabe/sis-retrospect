package com.sis.scrum.retrospect.hateoas.listener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PaginatedResultsRetrievedEventDiscoverabilityListenerTest {

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    private HttpServletResponse response;
    private PaginatedResultsRetrievedEventDiscoverabilityListener listener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        response = new MockHttpServletResponse();
        listener = new PaginatedResultsRetrievedEventDiscoverabilityListener();
    }

    @Test
    void shouldAddLinkHeaderOnPagedResourceRetrieval() {
        // Given
        int page = 1;
        int totalPages = 5;
        int pageSize = 10;
        Class<?> clazz = SomeClass.class;

        // Mock the behavior of UriComponentsBuilder methods
        UriComponentsBuilder newUriComponentsBuilder = UriComponentsBuilder.fromUriString("");
        when(uriComponentsBuilder.replaceQueryParam(eq("page"), anyInt())).thenReturn(newUriComponentsBuilder);
        when(uriComponentsBuilder.replaceQueryParam(eq("size"), anyInt())).thenReturn(newUriComponentsBuilder);

        // When
        listener.addLinkHeaderOnPagedResourceRetrieval(uriComponentsBuilder, response, clazz, page, totalPages, pageSize);

        // Then
        // Verify that the methods were called as expected
        verify(uriComponentsBuilder).replaceQueryParam("page", page + 1);
    }

    private static class SomeClass {
    }
}
