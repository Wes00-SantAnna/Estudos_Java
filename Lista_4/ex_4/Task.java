package ex_4;
import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate date;
    private String time;
    private String description;

    public Task(LocalDate date, String time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return date + " " + time + " - " + description;
    }
}