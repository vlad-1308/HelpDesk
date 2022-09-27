package ru.krasnik.helpdesk.Service;

import ru.krasnik.helpdesk.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Employee setRole(Employee employee);

}
