package com.carrentingsystem.service;

import com.carrentingsystem.entity.CarRental;
import com.carrentingsystem.entity.Customer;
import java.time.LocalDate;
import java.util.List;

public interface CarRentalService {
    List<CarRental> getAllRentals();
    CarRental getRentalById(CarRental carRental);
    CarRental saveRental(CarRental rental);
    void deleteRental(CarRental rental);
    List<CarRental> getRentalsByCustomer(Customer customer);
    List<CarRental> getRentalsByCustomerId(Long customerId);
    CarRental getRentalByCustomerIdAndCarId(Long customerId, Long carId);
    List<CarRental> getRentalReport(LocalDate startDate, LocalDate endDate);
}
