package com.carrentingsystem.service;

import com.carrentingsystem.entity.CarProducer;
import com.carrentingsystem.repository.CarProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarProducerServiceImpl implements CarProducerService {

    @Autowired
    private CarProducerRepository carProducerRepository;

    @Override
    public List<CarProducer> getAllProducers() {
        return carProducerRepository.findAll();
    }

    @Override
    public CarProducer getProducerById(Long id) {
        return carProducerRepository.findById(id).orElse(null);
    }

    @Override
    public CarProducer saveProducer(CarProducer producer) {
        return carProducerRepository.save(producer);
    }

    @Override
    public void deleteProducer(Long id) {
        carProducerRepository.deleteById(id);
    }
}
