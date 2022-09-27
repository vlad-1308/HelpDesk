package ru.krasnik.helpdesk.Service;

import org.springframework.stereotype.Service;
import ru.krasnik.helpdesk.Entity.Employee;
import ru.krasnik.helpdesk.Entity.Entry;

import java.util.List;


public interface EntryService {
    public List<Entry> getAllEntries();

    public Entry getEntry(int id);

    public Entry saveEntry(Entry entry);

    public List<Entry> getUsersEntries();

    public Entry confirmOrRejectEntry(Entry entry);

}
