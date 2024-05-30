package deniascafe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import deniascafe.demo.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}