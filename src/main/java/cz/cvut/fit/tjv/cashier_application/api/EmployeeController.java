package cz.cvut.fit.tjv.cashier_application.api;

import cz.cvut.fit.tjv.cashier_application.api.model.EmployeeDto;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.EmployeeDtoConverter;
import cz.cvut.fit.tjv.cashier_application.api.model.converter.OrderDtoConverter;
import cz.cvut.fit.tjv.cashier_application.business.EmployeeService;
import cz.cvut.fit.tjv.cashier_application.domain.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.StreamSupport;

/**
 * Rest controller class for Employee entity type.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController extends AbstractCrudController<Employee, EmployeeDto, Integer> {
    OrderDtoConverter orderDtoConverter;

    public EmployeeController(EmployeeService service, EmployeeDtoConverter employeeDtoConverter,
                              OrderDtoConverter orderDtoConverter) {
        super(service, employeeDtoConverter);
        this.orderDtoConverter = orderDtoConverter;
    }

    /**
     * Get DTOs of all employees that are not archived.
     *
     * @return collection of employee DTOs
     * @throws ResponseStatusException if there was an error during DTO conversion
     */
    @Override
    @GetMapping
    public Collection<EmployeeDto> readAll() {
        return StreamSupport.stream(((EmployeeService) service).readAllActive().spliterator(), false)
                .map(dtoConverter::toDto)
                .toList();
    }

    /**
     * Get DTOs of all archived employees.
     *
     * @return collection of employee DTOs
     * @throws ResponseStatusException if there was an error during DTO conversion
     */
    @GetMapping("/archive")
    public Collection<EmployeeDto> readArchived() {
        return StreamSupport.stream(((EmployeeService) service).readAllArchived().spliterator(), false)
                .map(dtoConverter::toDto)
                .toList();
    }

    /**
     * Calculate salary bonus of an employee for a certain time period.
     *
     * @param id id of the employee
     * @param from start date
     * @param to end date
     * @return calculated bonus
     * @throws ResponseStatusException if there is no employee with this id
     */
    @GetMapping("/{id}/bonus")
    int calculateBonus(@PathVariable Integer id, @RequestParam LocalDate from, @RequestParam LocalDate to) throws ResponseStatusException {
        try {
            return ((EmployeeService) service).calculateBonus(id, from, to);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update data of an employee.
     *
     * @param id id of the employee
     * @param dto DTO with new data
     * @return DTO of the updated employee
     * @throws ResponseStatusException if there is no employee with this id
     */
    @PutMapping("/{id}")
    EmployeeDto update(@PathVariable Integer id, @RequestBody EmployeeDto dto) throws ResponseStatusException {
        return service.readById(id).map(employee -> {
            employee.setName(dto.getName());
            employee.setSurname(dto.getSurname());
            employee.setPosition(dto.getPosition());
            employee.setSalary(dto.getSalary());
            return dtoConverter.toDto(service.update(employee));
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Archive an employee by id.
     *
     * @param id id of the employee
     * @throws ResponseStatusException if there is no menu item with this id
     */
    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws ResponseStatusException {
        Employee employee = service.readById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employee.setArchived(true);
        service.update(employee);
    }
}
