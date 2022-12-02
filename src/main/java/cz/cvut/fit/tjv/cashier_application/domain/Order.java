package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Domain type Order. Uses int as a primary key.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue
    private int id;
    /**
     * Total price of all included menu items.
     */
    private int price;
    /**
     * Date and time of order creation.
     */
    private LocalDateTime dateTime;
    /**
     * Employee that created this order.
     */
    @ManyToOne
    private Employee employee;
    /**
     * Menu item orders included in this order.
     */
    @OneToMany(mappedBy = "order")
    private final Set<MenuItemOrder> includedMenuItemOrders;

    /**
     * Create new instance of class Order.
     *
     * @param employee employee that created this order
     * @param menuItemOrders menu items to include and their counts
     */
    public Order(Employee employee, Set<MenuItemOrder> menuItemOrders) {
        this.price = menuItemOrders.stream()
                .map(itemOrder -> itemOrder.getItemCount() * itemOrder.getItemPrice())
                .reduce(0, Integer::sum);
        this.dateTime = LocalDateTime.now();
        this.employee = employee;
        this.includedMenuItemOrders = menuItemOrders;
    }

    public Order() {
        includedMenuItemOrders = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
     * Return included menu items.
     *
     * @return unmodifiable set of included menu items
     */
    public Set<MenuItemOrder> getIncludedMenuItemOrders() {
        return Collections.unmodifiableSet(includedMenuItemOrders);
    }
}
