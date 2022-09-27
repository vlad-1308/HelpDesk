package ru.krasnik.helpdesk.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.krasnik.helpdesk.Dao.EmployeeRepository;
import ru.krasnik.helpdesk.Dao.EntryDAOImpl;
import ru.krasnik.helpdesk.Entity.Employee;
import ru.krasnik.helpdesk.Entity.Entry;
import ru.krasnik.helpdesk.Entity.Role;
import ru.krasnik.helpdesk.Entity.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService{

    private final EmployeeRepository employeeRepository;
    @Autowired
    private EntryDAOImpl entryDAO;

    @Override
    @Transactional
    public List<Entry> getAllEntries() {
        return entryDAO.getAllEntries();
    }

    @Override
    @Transactional
    public List<Entry> getUsersEntries() {
        Employee employee = employeeRepository
                .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return entryDAO.getUsersEntries(employee);
    }

    @Override
    @Transactional
    public Entry getEntry(int id) {
        return entryDAO.getEntry(id);
    }

    @Override
    @Transactional
    public Entry saveEntry(Entry entry) {
        Employee employee = employeeRepository
                .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (employee.getAuthorities().contains(Role.ROLE_USER)) {
            if (entry.getStatus() == Status.SENT) {
                Entry entryToSent = entryDAO.getEntry(entry.getId());
                entryToSent.setStatus(Status.SENT);
                entryDAO.changeStatus(entryToSent, employee);
                return entryToSent;
            }
            return entryDAO.saveEntry(entry, employee);
        }
        else
            return null;
    }

    @Override
    @Transactional
    public Entry confirmOrRejectEntry(Entry entry) {
        Employee employee = employeeRepository
                .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Entry entryToConfirm = entryDAO.getEntry(entry.getId());
        if (employee.getAuthorities().contains(Role.ROLE_OPERATOR) && entryToConfirm.getStatus() == Status.SENT)
        {
            entryToConfirm.setStatus(entry.getStatus());
            entryDAO.changeStatus(entryToConfirm, employee);
            return entryToConfirm;
        } else
            return null;
    }


}
