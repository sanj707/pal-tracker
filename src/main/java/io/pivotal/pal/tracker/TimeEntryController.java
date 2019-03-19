package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeRepo;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo, MeterRegistry meterRegistry) {

        this.timeRepo = timeEntriesRepo;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }


    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        return new ResponseEntity<>(timeRepo.list(), HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

        TimeEntry createdTimeEntry =timeRepo.create(timeEntry);
           actionCounter.increment();
           timeEntrySummary.record(timeRepo.list().size());
        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);

    }
   @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntryById = timeRepo.find(id);
        if (timeEntryById != null) {
            actionCounter.increment();
            return new ResponseEntity<>(timeEntryById, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {

        timeRepo.delete(id);
        actionCounter.increment();
        timeEntrySummary.record(timeRepo.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedEntries = timeRepo.update(id, timeEntry);
        if (updatedEntries != null) {
            actionCounter.increment();
            return new ResponseEntity<>(updatedEntries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
