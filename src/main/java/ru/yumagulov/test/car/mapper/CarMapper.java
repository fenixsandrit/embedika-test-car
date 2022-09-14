package ru.yumagulov.test.car.mapper;

import org.mapstruct.Mapper;
import ru.yumagulov.test.car.dto.CarDto;
import ru.yumagulov.test.car.entity.Car;

@Mapper
public interface CarMapper {

    CarDto carToCarDto(Car car);
    Car carDtoToCar(CarDto carDto);
    Iterable<CarDto> carsToCarDtos(Iterable<Car> cars);
}
