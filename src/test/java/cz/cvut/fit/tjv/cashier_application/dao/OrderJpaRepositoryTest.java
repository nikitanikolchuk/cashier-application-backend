package cz.cvut.fit.tjv.cashier_application.dao;

import cz.cvut.fit.tjv.cashier_application.domain.Employee;
import cz.cvut.fit.tjv.cashier_application.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"
})
public class OrderJpaRepositoryTest {
    @Autowired
    EmployeeJpaRepository employeeJpaRepository;
    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Test
    void findAllByDateTimeBetween() {
        Employee employee = new Employee("testName", "testSurname", "barista", 15000);
        employeeJpaRepository.save(employee);

        Order order1 = new Order(employee);
        order1.setDateTime(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.MIN));
        orderJpaRepository.save(order1);

        Order order2 = new Order(employee);
        order2.setDateTime(LocalDateTime.of(LocalDate.of(2023, 3, 15), LocalTime.MIN));
        orderJpaRepository.save(order2);

        Order order3 = new Order(employee);
        order3.setDateTime(LocalDateTime.of(LocalDate.of(2023, 3, 25), LocalTime.MIN));
        orderJpaRepository.save(order3);

        Order order4 = new Order(employee);
        order4.setDateTime(LocalDateTime.of(LocalDate.of(2023, 6, 1), LocalTime.MIN));
        orderJpaRepository.save(order4);

        LocalDateTime from1 = LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.MIN);
        LocalDateTime to1 = LocalDateTime.of(LocalDate.of(2023, 3, 20), LocalTime.MIN);
        Collection<Order> result1 = orderJpaRepository.findAllByDateTimeBetween(from1, to1);
        Assertions.assertEquals(2, result1.size());

        LocalDateTime from2 = LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.MIN);
        LocalDateTime to2 = LocalDateTime.of(LocalDate.of(2023, 6, 1), LocalTime.MIN);
        Collection<Order> result2 = orderJpaRepository.findAllByDateTimeBetween(from2, to2);
        Assertions.assertEquals(4, result2.size());
    }
}
