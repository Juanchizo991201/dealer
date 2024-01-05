package com.firstapp.crud.controller;

import com.firstapp.crud.model.dto.UpdateCarInfoDto;
import com.firstapp.crud.service.CarService;
import com.firstapp.crud.model.dto.UpdateCarPriceDto;
import com.firstapp.crud.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //    List all cars
    @GetMapping("/")
    public ResponseEntity<List<Car>> getCars() {
        try {
            return ResponseEntity.ok().body(this.carService.getCars());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get a car by registration
    @GetMapping("/{registration}")
    public ResponseEntity<Car> getCar(@PathVariable String registration) {
        try {
            if (this.carService.exists(registration)) {
                Car car = this.carService.getCarByRegistration(registration);
                return ResponseEntity.ok().body(car);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //    Add a car
    @PostMapping("/")
    public ResponseEntity<String> addCar(@RequestBody Car car) {
        try {
            if (car.getRegistration() != null && !this.carService.exists(car.getRegistration())) {
                carService.save(car);
                return ResponseEntity.ok().body(MessageFormat.format("Car with registration {0} added", car.getRegistration()));
            } else {
                return ResponseEntity.badRequest().body(MessageFormat.format("Car with registration {0} already exists", car.getRegistration()));
            }
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding the car");
        }
    }

    //    Update a car information
    @PutMapping("/")
    public ResponseEntity<Car> updateCar(@RequestBody UpdateCarInfoDto carDto) {
        try {
            if (this.carService.exists(carDto.getRegistration())) {
                Car car = this.carService.getCarByRegistration(carDto.getRegistration());
                car.setBrand(carDto.getBrand());
                car.setModel(carDto.getModel());
                car.setDisplacement(carDto.getDisplacement());
                car.setPower(carDto.getPower());
                car.setColor(carDto.getColor());
                this.carService.save(car);
                return ResponseEntity.ok().body(car);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //    Update a car price
    @PutMapping("/update-price")
    public ResponseEntity<String> updateCarPrice(@RequestBody UpdateCarPriceDto dto) {
        try {
            if (this.carService.exists(dto.getRegistration())) {
                this.carService.updateCarPrice(dto);
                return ResponseEntity.ok().body(MessageFormat.format("Car with registration {0} updated", dto.getRegistration()));
            } else {
                return ResponseEntity.badRequest().body("Car with registration " + dto.getRegistration() + " does not exist");
            }
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating car price");
        }
    }

    //    Delete a car
    @DeleteMapping("/{registration}")
    public ResponseEntity<String> deleteCar(@PathVariable String registration) {
        try {
            if (this.carService.exists(registration)) {
                this.carService.deleteCar(registration);
                return ResponseEntity.ok().body(MessageFormat.format("Car with registration {0} deleted", registration));
            } else {
                return ResponseEntity.badRequest().body("Car with registration " + registration + " does not exist");
            }
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting car");
        }
    }
}
