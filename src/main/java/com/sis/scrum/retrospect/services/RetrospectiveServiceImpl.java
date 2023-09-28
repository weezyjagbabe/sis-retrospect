package com.sis.scrum.retrospect.services;

import com.sis.scrum.retrospect.models.Retrospective;
import com.sis.scrum.retrospect.models.dtos.CreateRetrospective;
import com.sis.scrum.retrospect.models.exceptions.ApiExceptions;
import com.sis.scrum.retrospect.models.exceptions.AppException;
import com.sis.scrum.retrospect.repositories.RetrospectiveRepository;
import com.sis.scrum.retrospect.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Retrospective service.
 */
@Slf4j
@Service
public class RetrospectiveServiceImpl implements RetrospectiveService{
    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Override
    public Retrospective createRetrospective(CreateRetrospective request) {
        return retrospectiveRepository.save(Retrospective.builder()
                .entryDate(request.getEntryDate())
                .name(request.getName())
                .summary(request.getSummary())
                .participants(request.getParticipants())
                .build());
    }

    @Override
    public Map<String, Object> findRetrospectives(int page, int size) {
        Map<String, Object> resp = new HashMap<>();
        Pageable paging = PageRequest.of(page, size);
        Page<Retrospective> pageRetro = this.retrospectiveRepository.findAll(paging);
        List<Retrospective> retrospectives = pageRetro.getContent();
        resp.put(Constants.RETROSPECTIVES, retrospectives);
        resp.put(Constants.CURRENT_PAGE, pageRetro.getNumber());
        resp.put(Constants.TOTAL_ITEMS, pageRetro.getTotalElements());
        resp.put(Constants.TOTAL_PAGES, pageRetro.getTotalPages());

        if (page > pageRetro.getTotalPages()) {
            throw new AppException(ApiExceptions.RESOURCE_NOT_FOUND);
        }
        return resp;
    }

    @Override
    public Map<String, Object> findRetrospectivesByDate(int page, int size, Date date) {
        Map<String, Object> resp = new HashMap<>();
        Pageable paging = PageRequest.of(page, size);
        Page<Retrospective> pageRetro = this.retrospectiveRepository.findByEntryDate(date,paging);
        List<Retrospective> retrospectives = pageRetro.getContent();
        resp.put(Constants.RETROSPECTIVES, retrospectives);
        resp.put(Constants.CURRENT_PAGE, pageRetro.getNumber());
        resp.put(Constants.TOTAL_ITEMS, pageRetro.getTotalElements());
        resp.put(Constants.TOTAL_PAGES, pageRetro.getTotalPages());

        if (page > pageRetro.getTotalPages()) {
            throw new AppException(ApiExceptions.RESOURCE_NOT_FOUND);
        }
        return resp;
    }
}
