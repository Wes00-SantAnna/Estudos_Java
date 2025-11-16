import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {
    private final ExecutorService executor;
    private final List<Runnable> tasks;

    public TaskExecutor(int numThreads) {
        this.executor = Executors.newFixedThreadPool(numThreads);
        this.tasks = new ArrayList<>();
    }

    public void submitTask(Runnable task) {
        tasks.add(task);
    }

    public void start() {
        for (Runnable task : tasks) {
            executor.submit(task);
        }
        executor.shutdown();
    }

    public void waitForCompletion() {
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
