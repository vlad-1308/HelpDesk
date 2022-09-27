package ru.krasnik.helpdesk.Dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.krasnik.helpdesk.Entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Employee.class, id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> listEmp = query.getResultList();
        return listEmp;
    }

    @Override
    public Employee setRole(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.update(employee);
        return employee;
    }
}
