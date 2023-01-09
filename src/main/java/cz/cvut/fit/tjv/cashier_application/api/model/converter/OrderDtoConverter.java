package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.OrderDetailDto;
import cz.cvut.fit.tjv.cashier_application.api.model.OrderDto;
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
public class OrderDtoConverter extends AbstractDtoConverter<Order, OrderDto> {
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
    public OrderDto toDto(Order entity) throws EntityNotFoundException {
        OrderDto dto = new OrderDto();
        dto.setId(Objects.requireNonNull(entity.getId()));
        dto.setDateTime(entity.getDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        dto.setEmployeeId(Objects.requireNonNull(entity.getEmployee().getId()));
        dto.setPrice(orderService.calculatePrice(entity.getId()));
        Collection<OrderDetailDto> details = entity.getOrderDetails().stream()
                .map(orderDetailDtoConverter::toDto)
                .toList();
        dto.setDetails(details);
        return dto;
    }

    @Override
    public Order toEntity(OrderDto dto) throws EntityNotFoundException {
        Employee employee = employeeService.readById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Not found employee with id = " + dto.getEmployeeId()));
        return new Order(employee);
    }
}
