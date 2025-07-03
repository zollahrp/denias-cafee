package deniascafe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import deniascafe.demo.model.Review;
import deniascafe.demo.repository.ReviewRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

     public Map<String, Double> calculateAverageRating() {
        List<Review> reviews = reviewRepository.findAll();
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Double> averages = new HashMap<>();

        totals.put("angry", 0);
        totals.put("sad", 0);
        totals.put("ok", 0);
        totals.put("good", 0);
        totals.put("happy", 0);

        for (Review review : reviews) {
            totals.put("angry", totals.get("angry") + review.getAngry());
            totals.put("sad", totals.get("sad") + review.getSad());
            totals.put("ok", totals.get("ok") + review.getOk());
            totals.put("good", totals.get("good") + review.getGood());
            totals.put("happy", totals.get("happy") + review.getHappy());
        }

        int totalReviews = reviews.size();
        if (totalReviews > 0) {
            averages.put("angry", (double) totals.get("angry") / totalReviews);
            averages.put("sad", (double) totals.get("sad") / totalReviews);
            averages.put("ok", (double) totals.get("ok") / totalReviews);
            averages.put("good", (double) totals.get("good") / totalReviews);
            averages.put("happy", (double) totals.get("happy") / totalReviews);
        } else {
            averages.put("angry", 0.0);
            averages.put("sad", 0.0);
            averages.put("ok", 0.0);
            averages.put("good", 0.0);
            averages.put("happy", 0.0);
        }

        return averages;
    }
}