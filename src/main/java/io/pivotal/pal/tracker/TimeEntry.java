package io.pivotal.pal.tracker;

import java.sql.Time;
import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }
    public TimeEntry(long id,long projectId, long userId, LocalDate date, int hours) {
        this.id=id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }
    public TimeEntry(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    @Override
    public String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", hours=" + hours +
                '}';
    }
    @Override
    public boolean equals(Object obj){

        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        TimeEntry timeEntry = (TimeEntry) obj;

        if (id != timeEntry.id)
            return false;
        if (projectId != timeEntry.projectId)
            return false;
        if (userId != timeEntry.userId)
            return false;
        if (hours != timeEntry.hours)
            return false;
        return date != null ? date.equals(timeEntry.date) : timeEntry.date == null;

    }
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        System.out.println(">>>>>>"+result);
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + hours;
        return result;
    }
}
