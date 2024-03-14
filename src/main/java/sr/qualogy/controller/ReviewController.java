package sr.qualogy.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.qualogy.entity.Review;
import sr.qualogy.service.ReviewService;

import java.util.List;

@Path("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAllReviews() {
        return reviewService.getReviews();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Review getReviewById(@PathParam("id") Long id) {
        return reviewService.getReviewById(id);
    }

    @POST
    @Path("/postReview")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveReview(Review review) {
        review = reviewService.saveReview(review);
        return Response.ok(review, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReviewById(@PathParam("id") Long id) {
        reviewService.deleteReviewById(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReview(Review review) {
        review = reviewService.updateReview(review);
        return Response.ok(review, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
