package cz.cvut.fit.tjv.cashier_application.api.model;

import java.util.Collection;

/**
 * Response Data Transfer Object class for Order entity type.
 */
public record OrderResponseDto(
        int id,
        String dateTime,
        int employeeId,
        int price,
        Collection<OrderDetailResponseDto> details
) {
}
