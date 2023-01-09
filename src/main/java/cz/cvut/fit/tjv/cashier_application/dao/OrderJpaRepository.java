package cz.cvut.fit.tjv.cashier_application.dao;

import cz.cvut.fit.tjv.cashier_application.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
    Collection<Order> findAllByDateTimeBetween(LocalDateTime from, LocalDateTime to);

    @Query(value = "SELECT o FROM Order o WHERE o.employee.id = :employeeId AND o.dateTime BETWEEN :from AND :to")
    Collection<Order> findAllByEmployeeIdAndDateTimeBetween(int employeeId, LocalDateTime from, LocalDateTime to);
}
