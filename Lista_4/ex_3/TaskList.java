import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Tarefas salvas em " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TaskList loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (TaskList) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }
        return new TaskList(); // retorna lista vazia se der erro
    }
}
