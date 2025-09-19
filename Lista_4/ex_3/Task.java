import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private String date;
    private String time;
    private String description;

    public Task(String date, String time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
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
