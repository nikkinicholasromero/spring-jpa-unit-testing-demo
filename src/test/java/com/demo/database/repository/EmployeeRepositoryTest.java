package com.demo.database.repository;

import com.demo.database.entity.Employee;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findAll() {
        List<Employee> employees = IterableUtils.toList(employeeRepository.findAll());
        assertThat(employees).isEmpty();

        Employee employee = new Employee(
                UUID.randomUUID().toString(),
                "Nikki Nicholas",
                "Romero",
                BigInteger.valueOf(3L),
                BigDecimal.valueOf(177.2),
                BigDecimal.valueOf(76.3),
                LocalDate.now(),
                LocalTime.now(),
                false);

        employeeRepository.save(employee);

        employees = IterableUtils.toList(employeeRepository.findAll());
        assertThat(employees).isNotEmpty();
        assertThat(employees.size()).isEqualTo(1L);
        assertThat(employees.get(0)).isEqualTo(employee);

        Employee fetchedEmployee = employeeRepository.findById(employee.id()).orElseThrow();
        assertThat(fetchedEmployee).isEqualTo(employee);
    }
}
