package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Response Data Transfer Object class for Employee entity type.
 */
public record EmployeeResponseDto(
        int id,
        String name,
        String surname,
        String position,
        int salary
) {
}
