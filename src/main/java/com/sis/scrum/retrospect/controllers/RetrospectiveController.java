package com.sis.scrum.retrospect.controllers;

import com.sis.scrum.retrospect.hateoas.web.PaginatedResultsRetrievedEvent;
import com.sis.scrum.retrospect.hateoas.web.ResourceCreatedEvent;
import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateRetrospective;
import com.sis.scrum.retrospect.services.RetrospectiveService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * The type Retrospective controller.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class RetrospectiveController extends BaseController {

    @Autowired
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    private final RetrospectiveService retrospectiveService;

    /**
     * Create response entity.
     *
     * @param request  the request
     * @param response the response
     * @return the response entity
     */
    @Operation(summary = "Create a new retrospective.")
    @PostMapping(value = "/retrospective",
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Retrospective> create(@RequestBody @Valid final CreateRetrospective request, final HttpServletResponse response) {
        Retrospective retrospective = retrospectiveService.createRetrospective(request);
        final String idOfCreatedResource = String.valueOf(retrospective.getId());

        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));
        return ResponseEntity.status(HttpStatus.CREATED).body(retrospective);
    }

    /**
     * Gets retrospectives.
     *
     * @param page       the page
     * @param size       the size
     * @param uriBuilder the uri builder
     * @param response   the response
     * @return the retrospectives
     */
    @Operation(summary = "Get all retrospectives")
    @GetMapping(value = "/retrospective",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Map<String, Object>> getRetrospectives(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response) {
        Map<String, Object> resp = retrospectiveService.findRetrospectives(page,size);

        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Retrospective>(
                Retrospective.class, uriBuilder, response, page, Integer.parseInt(resp.get("totalPages").toString()), size));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * Gets retrospectives by date.
     *
     * @param date       the date
     * @param page       the page
     * @param size       the size
     * @param uriBuilder the uri builder
     * @param response   the response
     * @return the retrospectives by date
     */
    @Operation(summary = "Get retrospectives - filter by date")
    @GetMapping(value = "/retrospective/search", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Map<String, Object>> getRetrospectivesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response) {
        Map<String, Object> resp = retrospectiveService.findRetrospectivesByDate(page,size,date);

        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Retrospective>(
                Retrospective.class, uriBuilder, response, page, Integer.parseInt(resp.get("totalPages").toString()), size));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
