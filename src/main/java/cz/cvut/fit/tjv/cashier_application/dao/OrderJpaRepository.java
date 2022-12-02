package cz.cvut.fit.tjv.cashier_application.dao;

import cz.cvut.fit.tjv.cashier_application.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
    Collection<Order> findAllByDateTimeAfter(LocalDateTime dateTime);

    @Query(value = "SELECT o FROM Order o WHERE o.employee.id = ?1 AND year(o.dateTime) = ?2 AND month(o.dateTime) = ?3")
    Collection<Order> findAllByEmployeeIdAndMonth(int employeeId, int year, int month);
}
