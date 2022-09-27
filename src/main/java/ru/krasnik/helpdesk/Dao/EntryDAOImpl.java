package ru.krasnik.helpdesk.Dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.krasnik.helpdesk.Entity.Employee;
import ru.krasnik.helpdesk.Entity.Entry;
import ru.krasnik.helpdesk.Entity.Role;
import ru.krasnik.helpdesk.Entity.Status;
import ru.krasnik.helpdesk.Service.CustomEmpDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class EntryDAOImpl implements EntryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Entry> getAllEntries() {
        Session session = entityManager.unwrap(Session.class);
        Query<Entry> query = session.createQuery("from Entry where status != 'DRAFT'", Entry.class);
        List<Entry> entryList = query.getResultList();
        return entryList;
    }

    @Override
    public List<Entry> getUsersEntries(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        Query<Entry> query = session.createQuery("from Entry ", Entry.class);
        List<Entry> entryList = employee.getEntries();
        return entryList;
    }

    @Override
    public Entry getEntry(int id) {
        Session session = entityManager.unwrap(Session.class);
        Entry entry = session.get(Entry.class, id);
        return entry;
    }

    @Override
    public Entry saveEntry(Entry entry, Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        if (entry.getId() == 0) {
            entry.setStatus(Status.DRAFT);
            entry.setCreatedEmp(employee);
            entry.setCreatedDate(new Date());
            employee.addEntryToEmployee(entry);
            session.save(entry);
            return entry;
        } else {
            Entry entryForUpdate = session.get(Entry.class, entry.getId());
            if (entryForUpdate.getStatus() == Status.DRAFT) {
                copyField(entry.getText(), entryForUpdate::setText);
                copyField(entry.getTheme(), entryForUpdate::setTheme);
                copyField(entry.getCreatedEmp(), entryForUpdate::setCreatedEmp);
                copyField(entry.getCreatedDate(), entryForUpdate::setCreatedDate);
                copyField(entry.getStatus(), entryForUpdate::setStatus);
                entryForUpdate.setModifiedDate(new Date());
                entryForUpdate.setModifiedEmp(employee);
                session.update(entryForUpdate);
                return entryForUpdate;
            } else
                  return entry;
        }

    }

    @Override
    public Entry changeStatus(Entry entry, Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        entry.setModifiedDate(new Date());
        entry.setModifiedEmp(employee);
        session.update(entry);
        return entry;
    }

    private static <T> void copyField(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
