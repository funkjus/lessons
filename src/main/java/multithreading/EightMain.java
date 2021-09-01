package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * producer consumer with
 * wait
 * notify
 */
public class EightMain {
    public static void main(String[] args) {
        ProducerAndConsumer w = new ProducerAndConsumer();

        Thread thread1 = new Thread(() -> w.producer());
        Thread thread2 = new Thread(() -> w.consumer());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ProducerAndConsumer {
    public Queue<Integer> queue = new LinkedList<>();
    public final int LIMIT = 10;
    private final Object lock = new Object();

    public void producer() {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consumer() {
        while (true) {
            synchronized (lock) {
                while (queue.size() == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = queue.poll();
                System.out.println(value);
                System.out.println("Size : " + queue.size());
                lock.notify();
            }
        }
    }
}
