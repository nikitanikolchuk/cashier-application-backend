package cz.cvut.fit.tjv.cashier_application.api.model;

/**
 * Data Transfer Object class for Employee entity type.
 */
public record EmployeeDto(int id, String name, String surname, String position, int salary) {
}
