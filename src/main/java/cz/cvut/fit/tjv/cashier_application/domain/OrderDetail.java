package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class implementing many-to-many relation for Order and MenuItem with additional attributes.
 */
@Entity
public class OrderDetail {
    @EmbeddedId
    OrderDetailKey key;
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
    int itemQuantity;
    /**
     * Price of the item at the moment of order creation.
     */
    int itemPrice;

    /**
     * Create new instance of class MenuItemOrder.
     *
     * @param order order that includes this menu item order
     * @param menuItem menu item in this menu item order
     * @param itemQuantity number of these items
     */
    public OrderDetail(Order order, MenuItem menuItem, int itemQuantity) {
        int orderId = Objects.requireNonNull(order.getId());
        int menuItemId = Objects.requireNonNull(menuItem.getId());
        this.key = new OrderDetailKey(orderId, menuItemId);
        this.order = order;
        this.menuItem = menuItem;
        this.itemQuantity = itemQuantity;
        this.itemPrice = menuItem.getPrice();
    }

    public OrderDetail(){
    }

    public OrderDetailKey getKey() {
        return key;
    }

    public void setKey(OrderDetailKey key) {
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

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
