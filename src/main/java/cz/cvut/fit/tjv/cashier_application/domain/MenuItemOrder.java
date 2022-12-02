package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.*;

/**
 * Class implementing many-to-many relation for Order and MenuItem with additional attributes.
 */
@Entity
public class MenuItemOrder {
    @EmbeddedId
    MenuItemOrderKey key;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "id_order")
    Order order;

    @ManyToOne
    @MapsId("menuItemId")
    @JoinColumn(name = "id_menu_item")
    MenuItem menuItem;

    /**
     * Number of ordered items with this id.
     */
    int itemCount;
    /**
     * Price of the item at the moment of order creation.
     */
    int itemPrice;

    /**
     * Create new instance of class MenuItemOrder.
     *
     * @param order order that includes this menu item order
     * @param menuItem menu item in this menu item order
     * @param itemCount number of these items
     */
    public MenuItemOrder(Order order, MenuItem menuItem, int itemCount) {
        this.key = new MenuItemOrderKey(order.getId(), menuItem.getId());
        this.order = order;
        this.menuItem = menuItem;
        this.itemCount = itemCount;
        this.itemPrice = menuItem.getPrice();
    }

    public MenuItemOrder(){
    }

    public MenuItemOrderKey getKey() {
        return key;
    }

    public void setKey(MenuItemOrderKey key) {
        this.key = key;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
