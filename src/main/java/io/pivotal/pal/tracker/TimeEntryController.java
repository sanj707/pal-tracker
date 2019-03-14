package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeRepo = timeEntriesRepo;
    }


    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeRepo.list(), HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

        TimeEntry createdTimeEntry =timeRepo.create(timeEntry);

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);

    }
   @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntryById = timeRepo.find(id);
        if (timeEntryById != null) {
            return new ResponseEntity<>(timeEntryById, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {

        timeRepo.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedEntries = timeRepo.update(id, timeEntry);
        if (updatedEntries != null) {
            return new ResponseEntity<>(updatedEntries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
