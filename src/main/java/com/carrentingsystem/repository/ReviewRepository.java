package com.carrentingsystem.repository;

import com.carrentingsystem.entity.Review;
import com.carrentingsystem.entity.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}
