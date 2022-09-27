package ru.krasnik.helpdesk.Dao;

import ru.krasnik.helpdesk.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployee();

    public Employee setRole(Employee employee);

    public Employee getEmployee(int id);
}
