package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.EmployeeDto;
import cz.cvut.fit.tjv.cashier_application.domain.Employee;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * DTO converter class for Employee and EmployeeDto types.
 */
@Component
public class EmployeeDtoConverter extends AbstractDtoConverter<Employee, EmployeeDto> {
    @Override
    public EmployeeDto toDto(Employee entity) {
        return new EmployeeDto(
                Objects.requireNonNull(entity.getId()),
                entity.getName(),
                entity.getSurname(),
                entity.getPosition(),
                entity.getSalary()
        );
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        return new Employee(dto.name(), dto.surname(), dto.position(), dto.salary());
    }
}
