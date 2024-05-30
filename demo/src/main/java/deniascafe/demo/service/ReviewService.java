package deniascafe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import deniascafe.demo.model.Review;
import deniascafe.demo.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void saveReview(String reviewType) {
        Review review = new Review();

        switch (reviewType) {
            case "angry":
                review.setAngry(1);
                break;
            case "sad":
                review.setSad(1);
                break;
            case "ok":
                review.setOk(1);
                break;
            case "good":
                review.setGood(1);
                break;
            case "happy":
                review.setHappy(1);
                break;
            default:
                throw new IllegalArgumentException("Unknown review type: " + reviewType);
        }

        reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void deleteReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.deleteById(id);
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    public void deleteCommentById(Long commentId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteCommentById'");
    }
}