package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Response Data Transfer Object class for OrderDetail entity type.
 */
public record OrderDetailResponseDto(
        int orderId,
        int itemId,
        String name,
        int price,
        int quantity
) {
}
