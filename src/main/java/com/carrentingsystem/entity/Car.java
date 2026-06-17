package com.carrentingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carID;

    @Column(name = "car_name", nullable = false, columnDefinition = "nvarchar(255)")
    private String carName;

    @Column(name = "car_model_year", nullable = false)
    private Integer carModelYear;

    @Column(name = "color", nullable = false, columnDefinition = "nvarchar(50)")
    private String color;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "description", nullable = false, columnDefinition = "nvarchar(MAX)")
    private String description;

    @Column(name = "import_date", nullable = false)
    private LocalDate importDate;

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private CarProducer producer;

    @Column(name = "rent_price", nullable = false)
    private Double rentPrice;

    @Column(name = "status", nullable = false, columnDefinition = "nvarchar(50)")
    private String status;

    @OneToMany(mappedBy = "car")
    private List<CarRental> carRentals;

    @OneToMany(mappedBy = "car")
    private List<Review> reviews;
}
