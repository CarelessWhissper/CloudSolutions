package sr.qualogy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sr.qualogy.entity.Review;

import java.util.List;


public class ReviewRepository {

    private EntityManager entityManager;

    public ReviewRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Review saveReview(Review review) {
        entityManager.getTransaction().begin();
        entityManager.persist(review);
        entityManager.getTransaction().commit();
        return review;
    }

    public List<Review> getReviews() {
        String sql = "select r from Review r";
        TypedQuery<Review> typedQuery = entityManager.createQuery(sql, Review.class);
        List<Review> reviews = typedQuery.getResultList();
        return reviews;
    }

    public void deleteReviewById(Long id) {
        Review review = entityManager.find(Review.class, id);
        if (review != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(review);
            entityManager.getTransaction().commit();
        }
    }

    public Review getReviewById(Long id) {
        return entityManager.find(Review.class, id);
    }

    public Review updateReview(Review review) {
        entityManager.getTransaction().begin();
        Review updatedReview = entityManager.merge(review);
        entityManager.getTransaction().commit();
        return updatedReview;
    }


}
