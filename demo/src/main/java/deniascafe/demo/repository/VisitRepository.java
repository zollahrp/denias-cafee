package deniascafe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import deniascafe.demo.model.Visit;


public interface VisitRepository extends JpaRepository<Visit, Long> {
}
