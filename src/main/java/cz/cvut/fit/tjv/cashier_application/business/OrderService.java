package cz.cvut.fit.tjv.cashier_application.business;

import cz.cvut.fit.tjv.cashier_application.dao.OrderDetailJpaRepository;
import cz.cvut.fit.tjv.cashier_application.dao.OrderJpaRepository;
import cz.cvut.fit.tjv.cashier_application.domain.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;

/**
 * Service class for Order entity type.
 */
@Service
public class OrderService extends AbstractCrudService<Order, Integer> {
    private final OrderDetailJpaRepository orderDetailJpaRepository;

    OrderService(OrderJpaRepository repository, OrderDetailJpaRepository orderDetailJpaRepository) {
        super(repository);
        this.orderDetailJpaRepository = orderDetailJpaRepository;
    }

    /**
     * Calculate price of the order.
     * Price of the order equals to sum of order details prices multiplied by quantities.
     *
     * @param id id of the order
     * @return calculated price
     */
    public int calculatePrice(int id) {
        return orderDetailJpaRepository.findAllByOrder_Id(id).stream()
                .map(orderDetail -> orderDetail.getItemQuantity() * orderDetail.getItemPrice())
                .reduce(0, Integer::sum);
    }

    /**
     * Find all orders created between two dates.
     *
     * @param from start date
     * @param to end date
     * @return collection of orders
     */
    public Collection<Order> findBetweenDates(LocalDate from, LocalDate to) {
        LocalDateTime timeFrom = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime timeTo = LocalDateTime.of(to, LocalTime.MAX);
        return ((OrderJpaRepository) repository).findAllByDateTimeBetween(timeFrom, timeTo);
    }

    /**
     * Find all orders create by a certain employee between two dates.
     *
     * @param employeeId id of employee that created this orders
     * @param from start date
     * @param to end date
     * @return collection of orders
     */
    public Collection<Order> findByEmployeeAndDatesBetween(int employeeId, LocalDate from, LocalDate to) {
        LocalDateTime fromTime = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime toTime = LocalDateTime.of(to, LocalTime.MAX);
        return ((OrderJpaRepository) repository).findAllByEmployeeIdAndDateTimeBetween(employeeId, fromTime, toTime);
    }

    /**
     * Calculate sum of prices of all orders created between two dates.
     *
     * @param from start date
     * @param to end date
     * @return calculated profit
     */
    public int countTotalProfitBetweenDates(LocalDate from, LocalDate to) {
        return findBetweenDates(from, to).stream()
                .map(order -> calculatePrice(Objects.requireNonNull(order.getId())))
                .reduce(0, Integer::sum);
    }

}
