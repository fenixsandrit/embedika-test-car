package ru.yumagulov.test.car.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yumagulov.test.car.dto.CarDto;
import ru.yumagulov.test.car.entity.Car;
import ru.yumagulov.test.car.exception.NumberAlreadyExistsException;
import ru.yumagulov.test.car.exception.ResouceNotFoundException;
import ru.yumagulov.test.car.mapper.CarMapper;
import ru.yumagulov.test.car.repository.CarRepository;

import java.util.LinkedHashMap;
import java.util.stream.StreamSupport;

@Service
@Log4j2
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    @Transactional
    public CarDto save(CarDto carDto) {
        if(carRepository.findFirstByNumber(carDto.getNumber()).isEmpty()) {
            var car = carRepository.save(carMapper.carDtoToCar(carDto));
            log.info("добавлена машина number={}",carDto.getNumber());
            return carMapper.carToCarDto(car);
        } else {
            log.warn("ошибка при добавление машины number={}",carDto.getNumber());
            throw new NumberAlreadyExistsException("The car with number=" + carDto.getNumber() + " is already exists");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            Car car = carRepository.findById(id).orElseThrow(
                    () -> new ResouceNotFoundException("The Сar with id=" + id + " was not found"));
            carRepository.delete(car);
            log.info("Машина id={} удалена успешно", car.getId());
        } catch (ResouceNotFoundException e) {
            log.warn("Ошибка при удаление машины id={}", id);
            throw e;
        }
    }

    @Override
    public Iterable<CarDto> findAll() {
        Iterable<Car> cars = carRepository.findAll();
        log.info("Вывод всех машин, найдено:{}", StreamSupport.stream(cars.spliterator(), false).count());
        return carMapper.carsToCarDtos(cars);
    }

    @Override
    public Object generateStatistic() {
        var statistic = new LinkedHashMap<String, Object>();
        statistic.put("Number of record",  StreamSupport.stream(findAll().spliterator(), false).count());
        if(carRepository.findFirstByOrderByCreatedDate().isPresent()) {
            statistic.put("First record",  carMapper.carToCarDto(carRepository.findFirstByOrderByCreatedDate().get()));
        }
        if(carRepository.findFirstByOrderByCreatedDateDesc().isPresent()) {
            statistic.put("Last record",  carMapper.carToCarDto(carRepository.findFirstByOrderByCreatedDateDesc().get()));
        }
        log.info("Статистика сформировалась успешно");
        return statistic;
    }
}
