package ru.yumagulov.test.car.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.yumagulov.test.car.dto.CarDto;
import ru.yumagulov.test.car.entity.Car;
import ru.yumagulov.test.car.exception.NumberAlreadyExistsException;
import ru.yumagulov.test.car.exception.ResouceNotFoundException;
import ru.yumagulov.test.car.mapper.CarMapperImpl;
import ru.yumagulov.test.car.repository.CarRepository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yumagulov.test.car.CarUtils.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CarServiceImpl.class, CarMapperImpl.class})
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void findAllTest() {
        Mockito.when(carRepository.findAll()).thenReturn(Collections.singletonList(new Car()));
        var carDtoList = carService.findAll();

        assertEquals(StreamSupport.stream(carDtoList.spliterator(), false).count(), 1);
    }

    @Test
    public void deleteSuccesTest() {
        Mockito.when(carRepository.findById(Mockito.any())).thenReturn(Optional.of(new Car()));

    }

    @Test
    public void deleteFailTest() {
        Mockito.when(carRepository.findById(Mockito.any()))
                .thenThrow(new ResouceNotFoundException("The Сar was not found"));
        Exception exception = assertThrows(ResouceNotFoundException.class, () -> {
            carService.delete(ID);
        });
        assertTrue(exception.getMessage().contains("The Сar was not found"));
    }

    @Test
    public void saveSuccesTest() {
        Mockito.when(carRepository.findFirstByNumber(Mockito.any()))
                .thenReturn(Optional.empty());
        Mockito.when(carRepository.save(Mockito.any()))
                .thenReturn(createCar());
        var carDtoBefore = createCarDto();
        var carDtoAfter = carService.save(carDtoBefore);

        assertEquals(carDtoBefore.getId(),carDtoAfter.getId());
        assertEquals(carDtoBefore.getNumber(),carDtoAfter.getNumber());
        assertEquals(carDtoBefore.getModel(),carDtoAfter.getModel());
        assertEquals(carDtoBefore.getColor(),carDtoAfter.getColor());
        assertEquals(carDtoBefore.getYear(),carDtoAfter.getYear());

    }

    @Test
    public void saveFailTest() {
        Mockito.when(carRepository.findFirstByNumber(Mockito.any()))
                .thenReturn(Optional.of(createCar()));
        Exception exception = assertThrows(NumberAlreadyExistsException.class, () -> {
            carService.save(createCarDto());
        });
        assertTrue(exception.getMessage().contains("The car with number=" + NUMBER + " is already exists"));
    }

    @Test
    public void generateStatisticTest() {
        Mockito.when(carRepository.findAll()).thenReturn(Collections.singletonList(new Car()));
        Mockito.when(carRepository.findFirstByOrderByCreatedDate()).thenReturn(Optional.of(createCar()));
        Mockito.when(carRepository.findFirstByOrderByCreatedDateDesc()).thenReturn(Optional.of(createCar()));
        var statistic = (LinkedHashMap<String, Object>) carService.generateStatistic();
        assertEquals(statistic.get("Number of record"), (long)1);
        assertEquals(((CarDto)statistic.get("First record")).getNumber(), NUMBER);
        assertEquals(((CarDto)statistic.get("First record")).getNumber(), NUMBER);
    }


}