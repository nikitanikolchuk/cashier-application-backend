package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Request Data Transfer Object class for Employee entity type.
 */
public record EmployeeRequestDto(
        String name,
        String surname,
        String position,
        int salary
) {
}
