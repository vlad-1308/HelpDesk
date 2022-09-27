package ru.krasnik.helpdesk.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.krasnik.helpdesk.Entity.Entry;
import ru.krasnik.helpdesk.Service.EntryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private EntryService entryService;

    @GetMapping("/entries/{id}")
    public Entry getEntry(@PathVariable int id) {
        Entry entry = entryService.getEntry(id);

        return entry;
    }

    @GetMapping("/entries")
    public List<Entry> showUsersEntries() {
        List<Entry> entryList = entryService.getUsersEntries();

        return entryList;
    }

    @PostMapping("/entries")
    public Entry addNewEntry(@RequestBody Entry entry) {
        entryService.saveEntry(entry);

        return entry;
    }

    @PutMapping("/entries")
    public Entry updateUsersEntry(@RequestBody Entry entry) {

        return entryService.saveEntry(entry);
    }



}
