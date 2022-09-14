package ru.yumagulov.test.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yumagulov.test.car.entity.Car;

import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findFirstByOrderByCreatedDate();

    Optional<Car> findFirstByOrderByCreatedDateDesc();

    Optional<Car> findFirstByNumber(String number);
}
