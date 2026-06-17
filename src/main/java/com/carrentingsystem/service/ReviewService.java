package com.carrentingsystem.service;

import com.carrentingsystem.entity.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review saveReview(Review review);
    void deleteReview(Review review);
}
