package cz.cvut.fit.tjv.cashier_application.domain;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class implementing many-to-many relation for Order and MenuItem with additional attributes.
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail implements Persistable<OrderDetailKey> {
    @EmbeddedId
    OrderDetailKey id;
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
    @Column(name = "quantity")
    int itemQuantity;

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
        this.id = new OrderDetailKey(orderId, menuItemId);
        this.order = order;
        this.menuItem = menuItem;
        this.itemQuantity = itemQuantity;
    }

    public OrderDetail(){
    }

    @Nullable
    @Override
    public OrderDetailKey getId() {
        return id;
    }

    public void setId(OrderDetailKey id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
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
}
