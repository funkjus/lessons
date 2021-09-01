package multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *  java.util.concurrent
 *  producer consumer
 *  with BlockingQueue
 * */
public class SixMain {
    public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> producer());
        Thread thread2 = new Thread(() -> consumer());
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void producer() {
        Random random = new Random();
        try {
            while (true) {
                queue.put(random.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void consumer() {
        Random random = new Random();
        try {
            while (true) {
                Thread.sleep(1);
                if (random.nextInt(10) == 5) {
                    System.out.println(queue.take());
                    System.out.println("Size : " + queue.size());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
