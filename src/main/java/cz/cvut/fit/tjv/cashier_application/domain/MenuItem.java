package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
     * Orders that included this menu item.
     */
    @OneToMany(mappedBy = "menuItem")
    private final Set<MenuItemOrder> containingOrders = new HashSet<>();

    /**
     * Create new instance of MenuItem class.
     *
     * @param name name of the item
     * @param price price of the item
     */
    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public MenuItem() {
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
     * Return orders that included this menu item.
     *
     * @return unmodifiable set of orders that included this menu item.
     */
    public Set<MenuItemOrder> getContainingOrders() {
        return Collections.unmodifiableSet(containingOrders);
    }

    /**
     * Add menu item order that included this item.
     *
     * @param order menu item order that included this item
     */
    public void addContainingOrder(MenuItemOrder order) {
        containingOrders.add(Objects.requireNonNull(order));
    }
}