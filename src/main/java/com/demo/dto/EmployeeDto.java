package com.demo.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        BigInteger numberOfDependents,
        BigDecimal height,
        BigDecimal weight,
        LocalDate hiredDate,
        LocalTime startTime,
        boolean isRegular
) {
}
