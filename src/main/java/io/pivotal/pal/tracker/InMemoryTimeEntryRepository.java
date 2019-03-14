package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private  HashMap<Long, TimeEntry> timeEntries=new HashMap<Long,TimeEntry>();


    private long currentId=1L;

    @Override
    public TimeEntry create(TimeEntry timeObject) {
        Long id=currentId++;
        TimeEntry createTimeEntry =new TimeEntry(
                id,
                timeObject.getProjectId(),
                timeObject.getUserId(),
                timeObject.getDate(),
                timeObject.getHours());
        timeEntries.put(id,createTimeEntry);

        return createTimeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (find(id) == null)
            return null;

        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntries.remove(id);
    }
}
