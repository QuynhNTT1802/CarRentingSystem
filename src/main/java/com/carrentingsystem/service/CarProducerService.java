package com.carrentingsystem.service;

import com.carrentingsystem.entity.CarProducer;
import java.util.List;

public interface CarProducerService {
    List<CarProducer> getAllProducers();
    CarProducer getProducerById(Long id);
    CarProducer saveProducer(CarProducer producer);
    void deleteProducer(Long id);
}
