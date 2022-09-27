package ru.krasnik.helpdesk.Dao;

import ru.krasnik.helpdesk.Entity.Employee;
import ru.krasnik.helpdesk.Entity.Entry;

import java.util.List;

public interface EntryDAO {
    public List<Entry> getAllEntries();

    public Entry getEntry(int id);

    public Entry saveEntry(Entry entry, Employee employee);

    public List<Entry> getUsersEntries(Employee employee);

    public Entry changeStatus(Entry entry, Employee employee);



}
