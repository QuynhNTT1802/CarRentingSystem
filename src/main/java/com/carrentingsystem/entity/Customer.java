package com.carrentingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;

    @Column(name = "customer_name", nullable = false, columnDefinition = "nvarchar(255)")
    private String customerName;

    @Column(name = "mobile", nullable = false, columnDefinition = "nvarchar(20)")
    private String mobile;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "identity_card", nullable = false, columnDefinition = "nvarchar(50)")
    private String identityCard;

    @Column(name = "licence_number", nullable = false, columnDefinition = "nvarchar(50)")
    private String licenceNumber;

    @Column(name = "licence_date", nullable = false)
    private LocalDate licenceDate;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "nvarchar(255)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "nvarchar(255)")
    private String password;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "customer")
    private List<CarRental> carRentals;

    @OneToMany(mappedBy = "customer")
    private List<Review> reviews;
}
