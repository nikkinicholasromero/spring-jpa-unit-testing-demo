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
    public void findAll_save_findById() {
        List<Employee> employees = IterableUtils.toList(employeeRepository.findAll());
        assertThat(employees).isEmpty();

        String id = UUID.randomUUID().toString();
        LocalDate hiredDate = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        Employee employee = new Employee(
                id,
                "Nikki Nicholas",
                "Romero",
                BigInteger.valueOf(3L),
                BigDecimal.valueOf(177.2),
                BigDecimal.valueOf(76.3),
                hiredDate,
                startTime,
                false);

        employeeRepository.save(employee);

        employees = IterableUtils.toList(employeeRepository.findAll());
        assertThat(employees).isNotEmpty();
        assertThat(employees.size()).isEqualTo(1L);
        assertThat(employees.get(0)).isEqualTo(employee);

        Employee fetchedEmployee = employeeRepository.findById(employee.id()).orElseThrow();
        assertThat(fetchedEmployee).isEqualTo(employee);
        assertThat(fetchedEmployee.id()).isEqualTo(id);
        assertThat(fetchedEmployee.firstName()).isEqualTo("Nikki Nicholas");
        assertThat(fetchedEmployee.lastName()).isEqualTo("Romero");
        assertThat(fetchedEmployee.numberOfDependents()).isEqualTo(BigInteger.valueOf(3L));
        assertThat(fetchedEmployee.height()).isEqualTo(BigDecimal.valueOf((177.2)));
        assertThat(fetchedEmployee.weight()).isEqualTo(BigDecimal.valueOf(76.3));
        assertThat(fetchedEmployee.hiredDate()).isEqualTo(hiredDate);
        assertThat(fetchedEmployee.startTime()).isEqualTo(startTime);
        assertThat(fetchedEmployee.isRegular()).isFalse();
    }
}
