package ru.yumagulov.test.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yumagulov.test.car.dto.CarDto;
import ru.yumagulov.test.car.service.CarService;

import javax.validation.Valid;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity getAllCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarDto car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable(value = "id") Long carId) {
        carService.delete(carId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistic")
    public ResponseEntity getStatistic() {
        return ResponseEntity.ok(carService.generateStatistic());
    }

}
