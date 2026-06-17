package com.carrentingsystem.service;

import com.carrentingsystem.entity.Car;
import com.carrentingsystem.repository.CarRentalRepository;
import com.carrentingsystem.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarRentalRepository carRentalRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            boolean hasRentals = carRentalRepository.existsByCar(car);
            if (hasRentals) {
                car.setStatus("Inactive");
                carRepository.save(car);
            } else {
                carRepository.delete(car);
            }
        }
    }

    @Override
    public List<Car> getCarsByStatus(String status) {
        return carRepository.findByStatus(status);
    }
}
