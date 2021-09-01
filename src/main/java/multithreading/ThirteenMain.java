package multithreading;

import java.util.Random;

public class ThirteenMain {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1_000_000_000; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Catch was interrupt...");
                    break;
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread was interrupt...");
                    break;
                }
                Math.sin(random.nextDouble());
            }
        });

        thread.start();
        System.out.println("Thread start...");
        thread.interrupt();
        thread.join();
        System.out.println("Thread end.");
    }
}
