package com.carrentingsystem.repository;

import com.carrentingsystem.entity.CarRental;
import com.carrentingsystem.entity.CarRentalId;
import com.carrentingsystem.entity.Customer;
import com.carrentingsystem.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, CarRentalId> {
    List<CarRental> findByCustomer(Customer customer);

    List<CarRental> findByCustomer_CustomerID(Long customerId);

    boolean existsByCar(Car car);

    @Query("SELECT r FROM CarRental r WHERE r.pickupDate >= :startDate AND r.pickupDate <= :endDate ORDER BY r.pickupDate DESC")
    List<CarRental> findByPickupDateBetweenOrderByPickupDateDesc(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
