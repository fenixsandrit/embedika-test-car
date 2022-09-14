package ru.yumagulov.test.car.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yumagulov.test.car.CarUtils.createCar;
import static ru.yumagulov.test.car.CarUtils.createCarDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CarMapperImpl.class})
class CarMapperTest {

    @Autowired
    private CarMapper carMapper;

    @Test
    public void carToCarDto() {
        var car = createCar();
        var carDto = carMapper.carToCarDto(car);
        assertEquals(car.getId(),carDto.getId());
        assertEquals(car.getNumber(),carDto.getNumber());
        assertEquals(car.getModel(),carDto.getModel());
        assertEquals(car.getColor(),carDto.getColor());
        assertEquals(car.getYear(),carDto.getYear());
    }

    @Test
    public void carDtoToCar() {
        var carDto = createCarDto();
        var car = carMapper.carDtoToCar(carDto);
        assertEquals(car.getId(),carDto.getId());
        assertEquals(car.getNumber(),carDto.getNumber());
        assertEquals(car.getModel(),carDto.getModel());
        assertEquals(car.getColor(),carDto.getColor());
        assertEquals(car.getYear(),carDto.getYear());
    }
    @Test
    public void carsToCarDtosTest() {
        var car = createCar();
        var listOfCars = Collections.singletonList(car);
        var listOfCarDtos = carMapper.carsToCarDtos(listOfCars);
        var carDto = listOfCarDtos.iterator().next();
        assertEquals(car.getId(),carDto.getId());
        assertEquals(car.getNumber(),carDto.getNumber());
        assertEquals(car.getModel(),carDto.getModel());
        assertEquals(car.getColor(),carDto.getColor());
        assertEquals(car.getYear(),carDto.getYear());
    }

}