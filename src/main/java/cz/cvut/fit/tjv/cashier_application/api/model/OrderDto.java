package cz.cvut.fit.tjv.cashier_application.api.model;

import java.util.Collection;

/**
 * Data Transfer Object class for Order entity type.
 */
public class OrderDto {
    private int id;
    private String dateTime;
    private int employeeId;
    private int price;
    private Collection<OrderDetailDto> details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Collection<OrderDetailDto> getDetails() {
        return details;
    }

    public void setDetails(Collection<OrderDetailDto> details) {
        this.details = details;
    }
}
