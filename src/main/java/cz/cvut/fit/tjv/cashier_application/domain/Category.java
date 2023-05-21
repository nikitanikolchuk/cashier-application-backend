package cz.cvut.fit.tjv.cashier_application.domain;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Domain type Category. Uses int as a primary key
 */
@Entity
public class Category implements Persistable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int id;
    private String name;

    /**
     * Menu items of this category.
     */
    @ManyToMany
    @JoinTable(
            name = "category_menu_item",
            joinColumns = @JoinColumn(name = "id_category"),
            inverseJoinColumns = @JoinColumn(name = "id_menu_item")
    )
    private Set<MenuItem> menuItems = new HashSet<>();

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

    /**
     * Delete a menu item from this category
     *
     * @param menuItem menu item to delete
     */
    public void removeMenuItem(MenuItem menuItem) {
        menuItems.remove(menuItem);
    }
}
