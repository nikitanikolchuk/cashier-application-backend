package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Domain type Order. Uses int as a primary key.
 */
@Entity
public class Order {
    @Id
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
     * Menu items included in this order.
     */
    @ManyToMany
    private final Set<MenuItem> includedMenuItems;

    /**
     * Create new instance of class Order.
     *
     * @param id id of the order
     * @param dateTime date and time of order creation
     * @param employee employee that created this order
     * @param includedMenuItems menu items to include
     */
    public Order(int id, LocalDateTime dateTime, Employee employee, Set<MenuItem> includedMenuItems) {
        this.id = id;
        this.price = includedMenuItems.stream().map(MenuItem::getPrice).reduce(0, Integer::sum);
        this.dateTime = dateTime;
        this.employee = employee;
        this.includedMenuItems = includedMenuItems;
    }

    public Order() {
        includedMenuItems = new HashSet<>();
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
    public Set<MenuItem> getIncludedMenuItems() {
        return Collections.unmodifiableSet(includedMenuItems);
    }
}
