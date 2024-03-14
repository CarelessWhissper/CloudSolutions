package sr.qualogy.service;


import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.Review;
import sr.qualogy.repository.ReviewRepository;

import java.util.List;

public class ReviewService {


    private final ReviewRepository reviewRepository;

    public ReviewService() {
        this.reviewRepository = new ReviewRepository(JPAConfiguration.getEntityManager());
    }

    public List<Review> getReviews() {
        return reviewRepository.getReviews();
    }

    public Review saveReview(Review review) {
        return reviewRepository.saveReview(review);
    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteReviewById(id);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.getReviewById(id);
    }

    public Review updateReview(Review review) {
        return reviewRepository.updateReview(review);
    }
}
