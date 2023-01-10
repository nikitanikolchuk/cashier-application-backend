package cz.cvut.fit.tjv.cashier_application.api;

import cz.cvut.fit.tjv.cashier_application.api.model.OrderDetailDto;
import cz.cvut.fit.tjv.cashier_application.api.model.OrderDto;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.OrderDtoConverter;
import cz.cvut.fit.tjv.cashier_application.business.MenuItemService;
import cz.cvut.fit.tjv.cashier_application.business.OrderDetailService;
import cz.cvut.fit.tjv.cashier_application.business.OrderService;
import cz.cvut.fit.tjv.cashier_application.domain.MenuItem;
import cz.cvut.fit.tjv.cashier_application.domain.Order;
import cz.cvut.fit.tjv.cashier_application.domain.OrderDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

/**
 * Rest controller class for Order entity type.
 */
@RestController
@RequestMapping("/orders")
public class OrderController extends AbstractCrudController<Order, OrderDto, Integer> {
    MenuItemService menuItemService;
    OrderDetailService orderDetailService;

    public OrderController(OrderService orderService, OrderDtoConverter converter,
                           MenuItemService menuItemService, OrderDetailService orderDetailService) {
        super(orderService, converter);
        this.menuItemService = menuItemService;
        this.orderDetailService = orderDetailService;
    }

    /**
     * Create a new order from given DTO.
     *
     * @param dto DTO to create order from
     * @return DTO of the created order
     * @throws ResponseStatusException if employee or one of the menu item ids is invalid
     */
    @Override
    @PostMapping
    public OrderDto create(@RequestBody OrderDto dto) throws ResponseStatusException {
        Order order = service.create(dtoConverter.toEntity(dto));
        if (order.getEmployee().isArchived())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        for (OrderDetailDto detailDto : dto.getDetails()) {
            MenuItem menuItem = menuItemService.readById(detailDto.getItemId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
            if (menuItem.isArchived())
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            OrderDetail detail = new OrderDetail(order, menuItem, detailDto.getQuantity());
            order.addOrderDetail(orderDetailService.create(detail));
        }
        return dtoConverter.toDto(order);
    }

    /**
     * Count profit from all orders for a certain time period.
     *
     * @param from start date
     * @param to end date
     * @return total profit
     */
    @GetMapping("/profit")
    public int countProfit(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return ((OrderService) service).countTotalProfitBetweenDates(from, to);
    }
}
