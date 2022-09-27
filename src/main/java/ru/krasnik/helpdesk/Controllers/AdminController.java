package ru.krasnik.helpdesk.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.krasnik.helpdesk.Entity.Employee;
import ru.krasnik.helpdesk.Entity.Entry;
import ru.krasnik.helpdesk.Service.EmployeeService;
import ru.krasnik.helpdesk.Service.EmployeeServiceImpl;
import ru.krasnik.helpdesk.Service.EntryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @PutMapping("/employees")
    public Employee setRoleForEmp(@RequestBody Employee employee) {
        return employee;
    }
}
