package com.firstapp.crud.service;

import com.firstapp.crud.model.dto.UpdateCarPriceDto;
import com.firstapp.crud.model.Car;
import com.firstapp.crud.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean exists(String registration) {
        return carRepository.existsById(registration);
    }

    public List<Car> getCars() {
        return carRepository.findAll();    }

    public Car getCarByRegistration(String registration) {
        return carRepository.findById(registration).orElse(null);
    }

    public Car save(Car car) {
        return this.carRepository.save(car);
    }

    public void deleteCar(String registration) {
        carRepository.deleteById(registration);
    }

    @Transactional
    public void updateCarPrice(UpdateCarPriceDto dto) {
        this.carRepository.updateCarPrice(dto);
    }


}
