package com.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateEmployeeRequest (
        @NotBlank String firstName,
        @NotBlank String lastName,
        @PositiveOrZero BigInteger numberOfDependents,
        @PositiveOrZero BigDecimal height,
        @PositiveOrZero BigDecimal weight,
        @NotNull LocalDate hiredDate,
        @NotNull LocalTime startTime,
        boolean isRegular){
}
