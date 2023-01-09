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
        OrderDetailDto dto = new OrderDetailDto();
        dto.setOrderId(Objects.requireNonNull(entity.getId()).getOrderId());
        dto.setItemId(entity.getId().getMenuItemId());
        MenuItem menuItem = menuItemService.readById(entity.getId().getMenuItemId())
                .orElseThrow(() -> new EntityNotFoundException("Not found menu item with id = " + dto.getItemId()));
        dto.setName(menuItem.getName());
        dto.setPrice(entity.getItemPrice());
        dto.setQuantity(entity.getItemQuantity());
        return dto;
    }

    @Override
    public OrderDetail toEntity(OrderDetailDto dto) throws EntityNotFoundException {
        Order order = orderService.readById(dto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Not found order with id = " + dto.getOrderId()));
        MenuItem menuItem = menuItemService.readById(dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Not found menu item with id = " + dto.getItemId()));
        return new OrderDetail(order, menuItem, dto.getQuantity());
    }
}
