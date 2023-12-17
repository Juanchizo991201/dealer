package com.firstapp.crud.car;

import com.firstapp.crud.car.dto.UpdateCarPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{registration}")
    public ResponseEntity<Car> getCar(@PathVariable String registration) {
        if (this.carService.exists(registration)) {
            return ResponseEntity.ok().body(this.carService.getCarByRegistration(registration));
        }
        return ResponseEntity.badRequest().body(null);
    }

//    Add a car
    @PostMapping("/")
    public ResponseEntity<String> addCar(@RequestBody Car car) {
        if (car.getRegistration() != null && !this.carService.exists(car.getRegistration())) {
            carService.save(car);
            return ResponseEntity.ok().body(MessageFormat.format("Car with registration {0} added", car.getRegistration()));
        }
        return ResponseEntity.badRequest().body("Car with registration " + car.getRegistration() + " already exists");
    }

//    Update a car
    @PutMapping("/")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        if (car.getRegistration() != null && this.carService.exists(car.getRegistration())){
            return ResponseEntity.ok().body(carService.save(car));
        }
        return ResponseEntity.badRequest().body(car);
    }

//    Update a car price
    @PutMapping("/update-price")
    public ResponseEntity<String> updateCarPrice(@RequestBody UpdateCarPriceDto dto){
        if (this.carService.exists(dto.getRegistration())){
            this.carService.updateCarPrice(dto);
            return ResponseEntity.ok().body(MessageFormat.format("Car with registration {0} updated", dto.getRegistration()));
        }
        return ResponseEntity.badRequest().body("Car with registration " + dto.getRegistration() + " does not exist");
    }

//    Delete a car
    @DeleteMapping("/{registration}")
    public ResponseEntity<String> deleteCar(@PathVariable String registration) {
        if (this.carService.exists(registration)) {
            this.carService.deleteCar(registration);
            return ResponseEntity.ok().body(MessageFormat.format("Car with registration {0} deleted", registration));
        }
        return ResponseEntity.badRequest().body("Car with registration " + registration + " does not exist");
    }
}
