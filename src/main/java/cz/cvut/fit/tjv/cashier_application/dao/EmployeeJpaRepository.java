package cz.cvut.fit.tjv.cashier_application.dao;

import cz.cvut.fit.tjv.cashier_application.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
    Collection<Employee> findAllByArchivedIsFalse();

    Collection<Employee> findAllByArchivedIsTrue();
}
