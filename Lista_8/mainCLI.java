import java.util.Scanner;

public class mainCLI {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Task Executor CLI ===");
        System.out.print("Quantos threads simultâneos deseja usar? ");
        int numThreads = scanner.nextInt();

        TaskExecutor executor = new TaskExecutor(numThreads);

        System.out.print("Quantas tarefas deseja criar? ");
        int numTasks = scanner.nextInt();

        for (int i = 1; i <= numTasks; i++) {
            System.out.print("Digite o número para a tarefa " + i + ": ");
            int value = scanner.nextInt();
            executor.submitTask(new Task(value));
        }

        System.out.println("\nIniciando execução...");
        executor.start();
        executor.waitForCompletion();

        System.out.println("\nTodas as tarefas foram concluídas!");
        scanner.close();
    }
}
