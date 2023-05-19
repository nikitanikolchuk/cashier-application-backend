package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Response Data Transfer Object class for Category entity type.
 */
public record CategoryResponseDto(
        int id,
        String name
) {
}
