package multithreading;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java.util.concurrent API
 * CountDownLatch
 */
public class NineMain {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(new TestCountDownLatch(countDownLatch, 1));
        executorService.shutdown();

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            countDownLatch.countDown();
            System.out.println(i + " broken");
        }
        System.out.println("All work done!");
    }
}

class TestCountDownLatch implements Runnable {
    private int id;
    private CountDownLatch countDownLatch;

    public TestCountDownLatch(CountDownLatch countDownLatch, int id) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread : " + id + " countdown");
        }
    }
}