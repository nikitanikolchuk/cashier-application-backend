package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Request Data Transfer Object class for MenuItem entity type.
 */
public record MenuItemRequestDto(
        String name,
        int price
) {
}
