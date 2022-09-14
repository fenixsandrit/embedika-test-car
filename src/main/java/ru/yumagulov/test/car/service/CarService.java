package ru.yumagulov.test.car.service;

import ru.yumagulov.test.car.dto.CarDto;

public interface CarService {

    CarDto save(CarDto carDto);

    void delete(Long id);

    Iterable<CarDto> findAll();

    Object generateStatistic();
}
