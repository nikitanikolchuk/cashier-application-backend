package cz.cvut.fit.tjv.cashier_application.api.model;

import java.util.Collection;

/**
 * Data Transfer Object class for Category entity type.
 */
public class CategoryDto {
    private int id;
    private String name;
    Collection<MenuItemDto> items;

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

    public Collection<MenuItemDto> getItems() {
        return items;
    }

    public void setItems(Collection<MenuItemDto> items) {
        this.items = items;
    }
}
