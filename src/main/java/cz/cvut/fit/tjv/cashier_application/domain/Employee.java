package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

/**
 * Domain type Employee. Uses int as a primary key.
 */
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private String surname;
    private String position;
    private int salary;
    /**
     * Orders served by this employee.
     */
    @OneToMany
    private final Set<Order> servedOrders = new HashSet<>();

    /**
     * Create new instance of Employee class.
     *
     * @param id id of the employee
     * @param name name of the employee
     * @param surname surname of the employee
     * @param position position of the employee, can be either "barista" or "manager"
     * @param salary salary of the employee
     */
    public Employee(int id, String name, String surname, String position, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.salary = salary;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Return orders served by this employee.
     *
     * @return unmodifiable set of served orders
     */
    public Set<Order> getServedOrders() {
        return Collections.unmodifiableSet(servedOrders);
    }

    /**
     * Add order served by this employee.
     *
     * @param order the order to add
     */
    public void addOrder(Order order) {
        servedOrders.add(Objects.requireNonNull(order));
    }
}