package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Response Data Transfer Object class for MenuItem entity type.
 */
public record MenuItemResponseDto(
        int id,
        String name,
        int price
) {
}
