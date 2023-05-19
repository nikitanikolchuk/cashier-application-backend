package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.OrderDetailRequestDto;
import cz.cvut.fit.tjv.cashier_application.api.model.OrderDetailResponseDto;
import cz.cvut.fit.tjv.cashier_application.business.MenuItemService;
import cz.cvut.fit.tjv.cashier_application.business.OrderService;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import cz.cvut.fit.tjv.cashier_application.domain.OrderDetail;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

/**
 * DTO converter class for OrderDetail and OrderDetailDto types.
 */
@Component
public class OrderDetailDtoConverter extends AbstractDtoConverter<OrderDetail, OrderDetailRequestDto, OrderDetailResponseDto> {
    OrderService orderService;
    MenuItemService menuItemService;

    public OrderDetailDtoConverter(OrderService orderService, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.menuItemService = menuItemService;
    }

    @Override
    public OrderDetailResponseDto toDto(OrderDetail entity) throws EntityNotFoundException {
        MenuItem menuItem = menuItemService.readById(Objects.requireNonNull(entity.getId()).getMenuItemId())
                .orElseThrow(() -> new EntityNotFoundException("Not found menu item with id = " + entity.getId().getMenuItemId()));

        return new OrderDetailResponseDto(
                Objects.requireNonNull(entity.getId()).getOrderId(),
                entity.getId().getMenuItemId(),
                menuItem.getName(),
                entity.getItemPrice(),
                entity.getItemQuantity()
        );
    }

    @Override
    public OrderDetail toEntity(OrderDetailRequestDto dto) throws EntityNotFoundException {
        throw new UnsupportedOperationException("Conversion directly to OrderDetail from DTO is not supported");
    }
}
