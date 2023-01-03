package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.*;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    /**
     * Menu items of this category.
     */
    @ManyToMany(mappedBy = "categories")
    private final Set<MenuItem> menuItems = new HashSet<>();

    /**
     * Crete an instance of class Category with given name.
     *
     * @param name name of the category
     */
    public Category(String name) {
        this.name = name;
    }

    public Category() {
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

    /**
     * Get menu items of this category.
     *
     * @return unmodifiable set of menu items
     */
    public Set<MenuItem> getMenuItems() {
        return Collections.unmodifiableSet(menuItems);
    }

    /**
     * Add a menu item to this category.
     *
     * @param menuItem menu item to add
     */
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(Objects.requireNonNull(menuItem));
    }
}
