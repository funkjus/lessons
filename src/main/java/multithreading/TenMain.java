package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * ReentrantLock
 */
public class TenMain {
    private int counter;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        TenMain tenMain = new TenMain();
        Thread thread1 = new Thread(() -> tenMain.increment());
        Thread thread2 = new Thread(() -> tenMain.increment());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        tenMain.showCounter();

    }

    private void increment() {
//        lock.lock();
        for (int i = 0; i < 10000; i++) {
            counter++;
        }
//        lock.unlock();
    }

    private void showCounter() {
        System.out.println(counter);
    }
}
