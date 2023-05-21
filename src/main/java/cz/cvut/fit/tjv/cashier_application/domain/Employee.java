package cz.cvut.fit.tjv.cashier_application.domain;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Domain type Employee. Uses int as a primary key.
 */
@Entity
public class Employee implements Persistable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private int id;
    private String name;
    private String surname;
    private String position;
    private int salary;
    private boolean archived = false;
    /**
     * Orders served by this employee.
     */
    @OneToMany(mappedBy = "employee")
    private final Set<Order> servedOrders = new HashSet<>();

    /**
     * Create new instance of Employee class.
     *
     * @param name     name of the employee
     * @param surname  surname of the employee
     * @param position position of the employee, can be either "barista" or "manager"
     * @param salary   salary of the employee
     */
    public Employee(String name, String surname, String position, int salary) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.salary = salary;
    }

    public Employee() {
    }

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
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

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
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
