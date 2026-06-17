package com.carrentingsystem.repository;

import com.carrentingsystem.entity.CarProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarProducerRepository extends JpaRepository<CarProducer, Long> {
}
