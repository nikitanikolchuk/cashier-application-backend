package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Key class for OrderDetail.
 */
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "id_order")
    int orderId;
    @Column(name = "id_menu_item")
    int menuItemId;

    public OrderDetailKey(int orderId, int menuItemId) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }

    public OrderDetailKey() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailKey that = (OrderDetailKey) o;
        return getOrderId() == that.getOrderId() && getMenuItemId() == that.getMenuItemId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, menuItemId);
    }
}
