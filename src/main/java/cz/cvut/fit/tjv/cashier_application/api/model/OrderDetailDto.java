package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Data Transfer Object class for OrderDetail entity type.
 */
public record OrderDetailDto(int orderId, int itemId, String name, int quantity, int price) {
}
