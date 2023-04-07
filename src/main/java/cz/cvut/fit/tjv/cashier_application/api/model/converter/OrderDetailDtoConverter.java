package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.OrderDetailDto;
import cz.cvut.fit.tjv.cashier_application.business.MenuItemService;
import cz.cvut.fit.tjv.cashier_application.business.OrderService;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import cz.cvut.fit.tjv.cashier_application.domain.Order;
import cz.cvut.fit.tjv.cashier_application.domain.OrderDetail;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

/**
 * DTO converter class for OrderDetail and OrderDetailDto types.
 */
@Component
public class OrderDetailDtoConverter extends AbstractDtoConverter<OrderDetail, OrderDetailDto> {
    OrderService orderService;
    MenuItemService menuItemService;

    public OrderDetailDtoConverter(OrderService orderService, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.menuItemService = menuItemService;
    }

    @Override
    public OrderDetailDto toDto(OrderDetail entity) throws EntityNotFoundException {
        MenuItem menuItem = menuItemService.readById(Objects.requireNonNull(entity.getId()).getMenuItemId())
                .orElseThrow(() -> new EntityNotFoundException("Not found menu item with id = " + entity.getId().getMenuItemId()));

        return new OrderDetailDto(
                Objects.requireNonNull(entity.getId()).getOrderId(),
                entity.getId().getMenuItemId(),
                menuItem.getName(),
                entity.getItemPrice(),
                entity.getItemQuantity()
        );
    }

    @Override
    public OrderDetail toEntity(OrderDetailDto dto) throws EntityNotFoundException {
        Order order = orderService.readById(dto.orderId())
                .orElseThrow(() -> new EntityNotFoundException("Not found order with id = " + dto.orderId()));
        MenuItem menuItem = menuItemService.readById(dto.itemId())
                .orElseThrow(() -> new EntityNotFoundException("Not found menu item with id = " + dto.itemId()));
        return new OrderDetail(order, menuItem, dto.quantity());
    }
}
