package ru.krasnik.helpdesk.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krasnik.helpdesk.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByLogin(String login);
}
