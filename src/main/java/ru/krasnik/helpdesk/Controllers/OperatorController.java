package ru.krasnik.helpdesk.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.krasnik.helpdesk.Entity.Entry;
import ru.krasnik.helpdesk.Service.EntryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/operator")
public class OperatorController {
    @Autowired
    private EntryService entryService;


    @GetMapping("operator/entries")
    public List<Entry> showAllEntries() {
        List<Entry> entryList = entryService.getAllEntries();

        return entryList;
    }

    @PutMapping("/operator/entries")
    public Entry confirmOrRejectEntry(@RequestBody Entry entry) {
        return entryService.confirmOrRejectEntry(entry);
    }
    @GetMapping("/user/operator/{id}")
    public Entry getEntry(@PathVariable int id) {
        Entry entry = entryService.getEntry(id);

        return entry;
    }

}
