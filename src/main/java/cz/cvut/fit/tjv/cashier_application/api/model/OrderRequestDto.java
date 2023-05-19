package cz.cvut.fit.tjv.cashier_application.api.model;

import java.util.Collection;

/**
 * Request Data Transfer Object class for Order entity type.
 */
public record OrderRequestDto(
        int employeeId,
        int price,
        Collection<OrderDetailRequestDto> details
) {
}
