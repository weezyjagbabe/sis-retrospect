package com.sis.scrum.retrospect.services;

import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateRetrospective;
import com.sis.scrum.retrospect.repositories.RetrospectiveRepository;
import com.sis.scrum.retrospect.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RetrospectiveServiceImplTest {

    @InjectMocks
    private RetrospectiveServiceImpl retrospectiveService;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRetrospective() {
        // Arrange
        CreateRetrospective request = new CreateRetrospective();
        request.setEntryDate(new Date());
        request.setName("Retrospective Name");
        request.setSummary("Retrospective Summary");
        request.setParticipants(Collections.singletonList("Participants"));

        Retrospective createdRetrospective = new Retrospective();
        createdRetrospective.setId(1L);
        createdRetrospective.setEntryDate(request.getEntryDate());
        createdRetrospective.setName(request.getName());
        createdRetrospective.setSummary(request.getSummary());
        createdRetrospective.setParticipants(request.getParticipants());

        when(retrospectiveRepository.save(any(Retrospective.class))).thenReturn(createdRetrospective);

        // Act
        Retrospective result = retrospectiveService.createRetrospective(request);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(request.getEntryDate(), result.getEntryDate());
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getSummary(), result.getSummary());
        assertEquals(request.getParticipants(), result.getParticipants());
    }

    @Test
    public void testFindRetrospectives() {
        // Arrange
        int page = 0;
        int size = 5;

        List<Retrospective> retrospectiveList = new ArrayList<>();
        retrospectiveList.add(new Retrospective());
        retrospectiveList.add(new Retrospective());

        Page<Retrospective> pageRetrospective = mock(Page.class);
        when(pageRetrospective.getContent()).thenReturn(retrospectiveList);
        when(pageRetrospective.getNumber()).thenReturn(page);
        when(pageRetrospective.getTotalElements()).thenReturn(10L);
        when(pageRetrospective.getTotalPages()).thenReturn(2);

        when(retrospectiveRepository.findAll(any(Pageable.class))).thenReturn(pageRetrospective);

        // Act
        Map<String, Object> result = retrospectiveService.findRetrospectives(page, size);

        // Assert
        assertNotNull(result);
        assertEquals(2, ((List<Retrospective>) result.get(Constants.RETROSPECTIVES)).size());
        assertEquals(page, result.get(Constants.CURRENT_PAGE));
        assertEquals(10L, result.get(Constants.TOTAL_ITEMS));
        assertEquals(2, result.get(Constants.TOTAL_PAGES));
    }

    @Test
    public void testFindRetrospectivesByDate() {
        // Arrange
        int page = 0;
        int size = 5;
        Date date = new Date();

        List<Retrospective> retrospectiveList = new ArrayList<>();
        retrospectiveList.add(new Retrospective());
        retrospectiveList.add(new Retrospective());

        Page<Retrospective> pageRetrospective = mock(Page.class);
        when(pageRetrospective.getContent()).thenReturn(retrospectiveList);
        when(pageRetrospective.getNumber()).thenReturn(page);
        when(pageRetrospective.getTotalElements()).thenReturn(10L);
        when(pageRetrospective.getTotalPages()).thenReturn(2);

        when(retrospectiveRepository.findByEntryDate(eq(date), any(Pageable.class))).thenReturn(pageRetrospective);

        // Act
        Map<String, Object> result = retrospectiveService.findRetrospectivesByDate(page, size, date);

        // Assert
        assertNotNull(result);
        assertEquals(2, ((List<Retrospective>) result.get(Constants.RETROSPECTIVES)).size());
        assertEquals(page, result.get(Constants.CURRENT_PAGE));
        assertEquals(10L, result.get(Constants.TOTAL_ITEMS));
        assertEquals(2, result.get(Constants.TOTAL_PAGES));
    }

    @Test
    public void testFindRetrospectivesByDateResourceNotFound() {
        // Arrange
        int page = -1;
        int size = 5;
        Date date = new Date();

        List<Retrospective> retrospectiveList = new ArrayList<>();
        retrospectiveList.add(new Retrospective());
        retrospectiveList.add(new Retrospective());

        Page<Retrospective> pageRetrospective = mock(Page.class);
        when(pageRetrospective.getContent()).thenReturn(retrospectiveList);
        when(pageRetrospective.getNumber()).thenReturn(page);
        when(pageRetrospective.getTotalElements()).thenReturn(10L);
        when(pageRetrospective.getTotalPages()).thenReturn(2);

        when(retrospectiveRepository.findByEntryDate(eq(date), any(Pageable.class))).thenReturn(pageRetrospective);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            retrospectiveService.findRetrospectivesByDate(page, size, date);
        }, "Expected exception for resource not found");
    }
}
