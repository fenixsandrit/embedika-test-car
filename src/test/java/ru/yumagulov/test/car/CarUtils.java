package ru.yumagulov.test.car;

import ru.yumagulov.test.car.dto.CarDto;
import ru.yumagulov.test.car.entity.Car;

public class CarUtils {

    public static final String NUMBER = "AA999A99";
    public static final String COLOR = "green";
    public static final String MODEL = "Skoda";
    public static final Integer YEAR = 2000;
    public static final Long ID = Long.valueOf(1);

    public static CarDto createCarDto() {
        var carDto = new CarDto();
        carDto.setId(ID);
        carDto.setNumber(NUMBER);
        carDto.setColor(COLOR);
        carDto.setModel(MODEL);
        carDto.setYear(YEAR);
        return carDto;
    }

    public static Car createCar() {
        var car = new Car();
        car.setId(ID);
        car.setNumber(NUMBER);
        car.setColor(COLOR);
        car.setModel(MODEL);
        car.setYear(YEAR);
        return car;
    }
}
