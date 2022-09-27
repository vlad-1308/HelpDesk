package ru.krasnik.helpdesk.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.krasnik.helpdesk.Dao.EmployeeDAO;
import ru.krasnik.helpdesk.Dao.EmployeeRepository;
import ru.krasnik.helpdesk.Entity.Employee;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository userRepository;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployee();
    }

    @Override
    @Transactional
    public Employee setRole(Employee employee) {
        Employee employeeForUpdate = employeeDAO.getEmployee(employee.getId());
        employeeForUpdate.setRoles(employee.getRoles());

        return employeeDAO.setRole(employeeForUpdate);
    }
}
