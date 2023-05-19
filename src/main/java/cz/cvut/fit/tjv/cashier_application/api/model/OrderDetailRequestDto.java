package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Request Data Transfer Object class for OrderDetail entity type.
 */
public record OrderDetailRequestDto(
        int itemId,
        String name,
        int quantity,
        int price
) {
}
