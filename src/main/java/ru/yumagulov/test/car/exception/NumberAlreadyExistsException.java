package ru.yumagulov.test.car.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumberAlreadyExistsException extends RuntimeException {
    public NumberAlreadyExistsException(String message) {
        super(message);
    }
}
