package ru.yumagulov.test.car.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;


@Data
@RequiredArgsConstructor
public class CarDto {

    private Long id;

    @NotBlank
    @NotNull
    @Pattern(regexp="^[A-Z\\-\\s]{2}[0-9]{3}(?<!0{3})[A-Z\\-\\s]{1}[0-9]{2}$", message= "not valid auto number, example:AA999A99(english letters)")
    private String number;

    @NotBlank
    @NotNull
    private String model;

    @NotNull
    @NotBlank
    private String color;

    @NotNull
    @Min(1900)
    @Max(2022)
    private Integer year;
}
