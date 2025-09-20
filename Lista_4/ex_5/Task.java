package ex_5;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable, Comparable<Task> {
    private static final long serialVersionUID = 1L;

    private final LocalDate date;
    private final String time;
    private final String description;

    public Task(LocalDate date, String time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Task other) {
        return this.date.compareTo(other.date);
    }

    @Override
    public String toString() {
        return date + " " + time + " - " + description;
    }
}
