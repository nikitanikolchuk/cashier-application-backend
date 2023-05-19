package cz.cvut.fit.tjv.cashier_application.api.model.converter;

import cz.cvut.fit.tjv.cashier_application.api.model.EmployeeRequestDto;
import cz.cvut.fit.tjv.cashier_application.api.model.EmployeeResponseDto;
import cz.cvut.fit.tjv.cashier_application.domain.Employee;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * DTO converter class for Employee and EmployeeDto types.
 */
@Component
public class EmployeeDtoConverter extends AbstractDtoConverter<Employee, EmployeeRequestDto, EmployeeResponseDto> {
    @Override
    public EmployeeResponseDto toDto(Employee entity) {
        return new EmployeeResponseDto(
                Objects.requireNonNull(entity.getId()),
                entity.getName(),
                entity.getSurname(),
                entity.getPosition(),
                entity.getSalary()
        );
    }

    @Override
    public Employee toEntity(EmployeeRequestDto dto) {
        return new Employee(dto.name(), dto.surname(), dto.position(), dto.salary());
    }
}
