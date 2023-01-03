package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.*;
import java.util.*;

/**
 * Domain type MenuItem. Uses int as a primary key.
 */
@Entity
public class MenuItem {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int price;
    /**
     * Order details that included this menu item.
     */
    @OneToMany(mappedBy = "menuItem")
    private final Set<OrderDetail> containingOrderDetails = new HashSet<>();
    /**
     * Categories to which this menu item belongs to.
     */
    @ManyToMany
    private Set<Category> categories;

    /**
     * Create new instance of MenuItem class.
     *
     * @param name name of the item
     * @param price price of the item
     * @param categories categories of the item
     */
    public MenuItem(String name, int price, Set<Category> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public MenuItem() {
        categories = new HashSet<>();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Return order details that included this menu item.
     *
     * @return unmodifiable set of order details that included this menu item.
     */
    public Set<OrderDetail> getContainingOrderDetails() {
        return Collections.unmodifiableSet(containingOrderDetails);
    }

    /**
     * Add order detail that included this item.
     *
     * @param orderDetail order detail that included this item
     */
    public void addContainingOrderDetail(OrderDetail orderDetail) {
        containingOrderDetails.add(Objects.requireNonNull(orderDetail));
    }

    /**
     * Get categories of this menu item.
     *
     * @return unmodifiable set of categories
     */
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    /**
     * Set categories of this menu item.
     *
     * @param categories categories to set
     */
    public void setCategories(Set<Category> categories) {
        this.categories = Objects.requireNonNull(categories);
    }
}