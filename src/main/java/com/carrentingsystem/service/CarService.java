package com.carrentingsystem.service;

import com.carrentingsystem.entity.Car;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Long id);
    Car saveCar(Car car);
    void deleteCar(Long id);
    List<Car> getCarsByStatus(String status);
}
