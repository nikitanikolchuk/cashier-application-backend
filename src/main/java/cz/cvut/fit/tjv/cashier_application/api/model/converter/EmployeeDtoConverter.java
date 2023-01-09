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
        EmployeeDto dto = new EmployeeDto();
        dto.setId(Objects.requireNonNull(entity.getId()));
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPosition(entity.getPosition());
        dto.setSalary(entity.getSalary());
        return dto;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        return new Employee(dto.getName(), dto.getSurname(), dto.getPosition(), dto.getSalary());
    }
}
