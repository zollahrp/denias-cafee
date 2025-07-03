package deniascafe.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deniascafe.demo.model.Visit;
import deniascafe.demo.repository.VisitRepository;


@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public Visit getVisit() {
        Optional<Visit> optionalVisit = visitRepository.findById(1L);
        return optionalVisit.orElseGet(() -> {
            Visit visit = new Visit(0L);
            visit.setId(1L);
            visitRepository.save(visit);
            return visit;
        });
    }

    public void incrementVisit() {
        Visit visit = getVisit();
        visit.setTotalVisits(visit.getTotalVisits() + 1);
        visitRepository.save(visit);
    }
}
