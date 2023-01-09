package cz.cvut.fit.tjv.cashier_application.business;

import cz.cvut.fit.tjv.cashier_application.dao.EmployeeJpaRepository;
import cz.cvut.fit.tjv.cashier_application.domain.Employee;
import cz.cvut.fit.tjv.cashier_application.domain.Order;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * Service class for Employee entity type.
 */
@Service
public class EmployeeService extends AbstractCrudService<Employee, Integer> {
    /**
     * Part of each order's price that employees get as a bonus in percent.
     */
    private final static double EMPLOYEE_BONUS = 0.1;
    private final OrderService orderService;

    EmployeeService(EmployeeJpaRepository repository, OrderService orderService) {
        super(repository);
        this.orderService = orderService;
    }

    /**
     * Find all orders created by employee with this id.
     *
     * @param employeeId id of the employee
     * @return collection of orders
     */
    public Collection<Order> findOrders(int employeeId) throws EntityNotFoundException {
        Employee e = repository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Not found employee with id = " + employeeId));
        return e.getServedOrders();
    }

    /**
     * Calculate salary bonus of an employee for a certain time period.
     * Bonus equals to EMPLOYEE_BONUS% of sum of order prices served by this employee.
     *
     * @param employeeId id of the employee
     * @param from start date
     * @param to end date
     * @return calculated bonus
     * @throws EntityNotFoundException if there is no employee with this id
     */
    public int calculateBonus(int employeeId, LocalDate from, LocalDate to) throws EntityNotFoundException {
        Optional<Employee> employee = repository.findById(employeeId);
        if (employee.isEmpty())
            throw new EntityNotFoundException("Not found employee with id = " + employeeId);
        Collection<Order> orders = orderService.findByEmployeeAndDatesBetween(employeeId, from, to);
        int orderPriceSum = orders.stream()
                .map(order -> orderService.calculatePrice(Objects.requireNonNull(order.getId())))
                .reduce(0, Integer::sum);
        return (int) (orderPriceSum * EMPLOYEE_BONUS);
    }
}
