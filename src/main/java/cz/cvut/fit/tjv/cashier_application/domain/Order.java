package cz.cvut.fit.tjv.cashier_application.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Domain type Order. Uses int as a primary key.
 */
@Entity
@Table(name = "\"order\"")
@AttributeOverride(name = "id", column = @Column(name = "id_order"))
public class Order extends AbstractPersistable<Integer> {
    /**
     * Local date and time of order creation.
     */
    private LocalDateTime dateTime;
    /**
     * Employee that created this order.
     */
    @ManyToOne
    private Employee employee;
    /**
     * Menu items with their prices and quantities included in this order.
     */
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private final Set<OrderDetail> orderDetails = new HashSet<>();

    /**
     * Create new instance of class Order.
     *
     * @param employee employee that created this order
     */
    public Order(Employee employee) {
        this.dateTime = LocalDateTime.now();
        this.employee = employee;
    }

    public Order() {
    }

    /**
     * Return date and time of this order creation.
     *
     * @return date and time of this order creation
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Set date and time of this order creation.
     *
     * @param dateTime date and time of this order creation
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Return employee that created the order.
     *
     * @return employee that created the order
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set employee that created the order.
     *
     * @param employee employee that created the order
     */
    public void setEmployee(Employee employee) {
        this.employee = Objects.requireNonNull(employee);
    }

    /**
     * Return included menu items with their quantities and prices.
     *
     * @return unmodifiable set of order details
     */
    public Set<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableSet(orderDetails);
    }

    /**
     * Add new order detail to the order.
     *
     * @param orderDetail order detail to add
     */
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(Objects.requireNonNull(orderDetail));
    }
}
