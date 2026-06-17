package com.carrentingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Review")
@IdClass(ReviewId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "review_star", nullable = false)
    private Integer reviewStar;

    @Column(name = "comment", nullable = false, columnDefinition = "nvarchar(MAX)")
    private String comment;
}
