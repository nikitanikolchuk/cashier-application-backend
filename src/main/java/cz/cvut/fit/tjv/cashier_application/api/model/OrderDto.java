package cz.cvut.fit.tjv.cashier_application.api.model;

import java.util.Collection;

/**
 * Data Transfer Object class for Order entity type.
 */
public record OrderDto(int id, String dateTime, int employeeId, int price, Collection<OrderDetailDto> details) {
}
