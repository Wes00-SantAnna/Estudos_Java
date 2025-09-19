public class Main {
    public static void main(String[] args) {
        
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("2023-10-01", "10:00", "Reunião com equipe"));
        taskList.addTask(new Task("2023-10-02", "14:00", "Estudar Java Serialization"));

        System.out.println("Tarefas atuais:");
        taskList.listTasks();

        String filename = "tasks.ser";
        taskList.saveToFile(filename);

        System.out.println("\nTarefas recuperadas do arquivo:");
        TaskList loadedTaskList = TaskList.loadFromFile(filename);
        loadedTaskList.listTasks();
    }
}
