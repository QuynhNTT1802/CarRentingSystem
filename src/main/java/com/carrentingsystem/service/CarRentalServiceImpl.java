package com.carrentingsystem.service;

import com.carrentingsystem.entity.CarRental;
import com.carrentingsystem.entity.CarRentalId;
import com.carrentingsystem.entity.Customer;
import com.carrentingsystem.repository.CarRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    private CarRentalRepository carRentalRepository;

    @Override
    public List<CarRental> getAllRentals() {
        return carRentalRepository.findAll();
    }

    @Override
    public CarRental getRentalById(CarRental carRental) {
        return carRentalRepository.findById(
            new CarRentalId(carRental.getCustomer().getCustomerID(), carRental.getCar().getCarID())
        ).orElse(null);
    }

    @Override
    public CarRental saveRental(CarRental rental) {
        return carRentalRepository.save(rental);
    }

    @Override
    public void deleteRental(CarRental rental) {
        carRentalRepository.delete(rental);
    }

    @Override
    public List<CarRental> getRentalsByCustomer(Customer customer) {
        return carRentalRepository.findByCustomer(customer);
    }

    @Override
    public List<CarRental> getRentalsByCustomerId(Long customerId) {
        return carRentalRepository.findByCustomer_CustomerID(customerId);
    }

    @Override
    public CarRental getRentalByCustomerIdAndCarId(Long customerId, Long carId) {
        return carRentalRepository.findById(new CarRentalId(customerId, carId)).orElse(null);
    }

    @Override
    public List<CarRental> getRentalReport(LocalDate startDate, LocalDate endDate) {
        return carRentalRepository.findByPickupDateBetweenOrderByPickupDateDesc(startDate, endDate);
    }
}
