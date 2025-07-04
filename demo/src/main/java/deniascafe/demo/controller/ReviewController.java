package deniascafe.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import deniascafe.demo.model.Review;
import deniascafe.demo.service.ReviewService;
import deniascafe.demo.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/submitReview")
    public ResponseEntity<String> submitReview(@RequestBody Map<String, String> payload) {
        try {
            String reviewType = payload.get("review");
            reviewService.saveReview(reviewType);
            return ResponseEntity.ok("Review submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting review");
        }
    }


    @GetMapping("/getReviewStats")
    public Map<String, Integer> getReviewStats() {
        List<Review> reviews = reviewRepository.findAll();
        Map<String, Integer> stats = new HashMap<>();
        stats.put("angry", 0);
        stats.put("sad", 0);
        stats.put("ok", 0);
        stats.put("good", 0);
        stats.put("happy", 0);

        for (Review review : reviews) {
            stats.put("angry", stats.get("angry") + review.getAngry());
            stats.put("sad", stats.get("sad") + review.getSad());
            stats.put("ok", stats.get("ok") + review.getOk());
            stats.put("good", stats.get("good") + review.getGood());
            stats.put("happy", stats.get("happy") + review.getHappy());
        }

        return stats;
    }

    @DeleteMapping("/review/{commentId}") // Update path variable to {commentId}
public ResponseEntity<Void> deleteReview(@PathVariable Long commentId) {
    logger.info("Received request to delete comment with id: " + commentId);
    try {
        reviewService.deleteCommentById(commentId); // Update method call to deleteCommentById
        logger.info("Successfully deleted comment with id: " + commentId);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        logger.error("Error deleting comment with id: " + commentId, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @GetMapping("/getAverageRating")
    public Map<String, Double> getAverageRating() {
        return reviewService.calculateAverageRating();
    }

    @GetMapping("/getDominantEmoji")
    public Map<String, String> getDominantEmoji() {
        List<Review> reviews = reviewRepository.findAll();
        Map<String, Double> averages = new HashMap<>();
        averages.put("angry", 0.0);
        averages.put("sad", 0.0);
        averages.put("ok", 0.0);
        averages.put("good", 0.0);
        averages.put("happy", 0.0);

        if (!reviews.isEmpty()) {
            int totalReviews = reviews.size();
            for (Review review : reviews) {
                averages.put("angry", averages.get("angry") + review.getAngry());
                averages.put("sad", averages.get("sad") + review.getSad());
                averages.put("ok", averages.get("ok") + review.getOk());
                averages.put("good", averages.get("good") + review.getGood());
                averages.put("happy", averages.get("happy") + review.getHappy());
            }
            averages.replaceAll((k, v) -> v / totalReviews);
        }

        String dominantEmoji = averages.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("no reviews");

        Map<String, String> response = new HashMap<>();
        response.put("dominantEmoji", dominantEmoji);

        return response;
    }

}