public class Task implements Runnable{
    private final int number;

    public Task(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        long result = factorial(number);
        System.out.println("Task for number " + number + " finished. Result = " + result);
    }

    private long factorial(int n) {
        long total = 1;
        for (int i = 0; i <= n; i++) {
            total += i;
        }
        return total;
    }
}
