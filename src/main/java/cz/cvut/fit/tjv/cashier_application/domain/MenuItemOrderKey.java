package cz.cvut.fit.tjv.cashier_application.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Key class for OrderedMenuItem.
 */
@Embeddable
public class MenuItemOrderKey implements Serializable {
    @Column(name = "id_order")
    int orderId;
    @Column(name = "id_menu_item")
    int menuItemId;

    public MenuItemOrderKey(int orderId, int menuItemId) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }

    public MenuItemOrderKey() {
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
}
