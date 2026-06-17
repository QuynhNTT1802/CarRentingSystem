package com.carrentingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "CarProducer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private Long producerID;

    @Column(name = "producer_name", nullable = false, columnDefinition = "nvarchar(255)")
    private String producerName;

    @Column(name = "address", nullable = false, columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "country", nullable = false, columnDefinition = "nvarchar(100)")
    private String country;

    @OneToMany(mappedBy = "producer")
    private List<Car> cars;
}
