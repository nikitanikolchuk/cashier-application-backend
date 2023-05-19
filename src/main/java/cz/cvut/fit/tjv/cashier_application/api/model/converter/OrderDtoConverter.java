package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.OrderDetailResponseDto;
import cz.cvut.fit.tjv.cashier_application.api.model.OrderRequestDto;
import cz.cvut.fit.tjv.cashier_application.api.model.OrderResponseDto;
import cz.cvut.fit.tjv.cashier_application.business.EmployeeService;
import cz.cvut.fit.tjv.cashier_application.business.OrderService;
import cz.cvut.fit.tjv.cashier_application.domain.Employee;
import cz.cvut.fit.tjv.cashier_application.domain.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

/**
 * DTO converter class for Order and OrderDto types.
 */
@Component
public class OrderDtoConverter extends AbstractDtoConverter<Order, OrderRequestDto, OrderResponseDto> {
    OrderService orderService;
    EmployeeService employeeService;
    OrderDetailDtoConverter orderDetailDtoConverter;
    public OrderDtoConverter(EmployeeService employeeService, OrderService orderService,
                             OrderDetailDtoConverter orderDetailDtoConverter) {
        this.orderService = orderService;
        this.employeeService = employeeService;
        this.orderDetailDtoConverter = orderDetailDtoConverter;
    }

    @Override
    public OrderResponseDto toDto(Order entity) throws EntityNotFoundException {
        Collection<OrderDetailResponseDto> details = entity.getOrderDetails().stream()
                .map(orderDetailDtoConverter::toDto)
                .toList();

        return new OrderResponseDto(
                Objects.requireNonNull(entity.getId()),
                entity.getDateTime().format(DateTimeFormatter.ISO_DATE_TIME),
                Objects.requireNonNull(entity.getEmployee().getId()),
                orderService.calculatePrice(entity.getId()),
                details
        );
    }

    @Override
    public Order toEntity(OrderRequestDto dto) throws EntityNotFoundException {
        Employee employee = employeeService.readById(dto.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Not found employee with id = " + dto.employeeId()));
        return new Order(employee);
    }
}
