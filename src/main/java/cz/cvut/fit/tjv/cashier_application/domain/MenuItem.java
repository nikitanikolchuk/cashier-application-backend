package cz.cvut.fit.tjv.cashier_application.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.*;

/**
 * Domain type MenuItem. Uses int as a primary key.
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "id_menu_item"))
public class MenuItem extends AbstractPersistable<Integer> {
    private String name;
    private int price;
    private boolean archived = false;
    /**
     * Order details that included this menu item.
     */
    @OneToMany(mappedBy = "menuItem")
    private final Set<OrderDetail> containingOrderDetails = new HashSet<>();
    /**
     * Categories to which this menu item belongs to.
     */
    @ManyToMany(mappedBy = "menuItems")
    private final Set<Category> categories = new HashSet<>();

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

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
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
     * Add category to this menu item.
     *
     * @param category category to add
     */
    public void addCategory(Category category) {
        this.categories.add(Objects.requireNonNull(category));
    }

    /**
     * Delete this item from all its categories.
     */
    public void deleteFromCategories() {
        for (Category category : categories)
            category.removeMenuItem(this);
    }
}