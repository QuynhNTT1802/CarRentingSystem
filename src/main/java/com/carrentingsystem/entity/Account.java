package com.carrentingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountID;

    @Column(name = "account_name", nullable = false, columnDefinition = "nvarchar(255)")
    private String accountName;

    @Column(name = "role", nullable = false, columnDefinition = "nvarchar(50)")
    private String role;

    @OneToOne(mappedBy = "account")
    private Customer customer;
}
