package com.carrentingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "CarRental")
@IdClass(CarRentalId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRental {
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "pickup_date", nullable = false)
    private LocalDate pickupDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "rent_price", nullable = false)
    private Double rentPrice;

    @Column(name = "status", nullable = false, columnDefinition = "nvarchar(50)")
    private String status;
}
