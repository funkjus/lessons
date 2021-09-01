package multithreading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  lock1 lock2 ReentrantLock method vs DEADLOCK!
 */
public class TwelveMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Thread thread1 = new Thread(() -> Acount.threadOne());
        Thread thread2 = new Thread(() -> Acount.threadTwo());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Acount.getBalance();
    }
}

class Acount {

    static Acount acount1 = new Acount();
    static Acount acount2 = new Acount();
    public int amount = 10000;
    public static Lock lock1 = new ReentrantLock();
    public static Lock lock2 = new ReentrantLock();

    public void upAmount(int sum) {
        amount += sum;
    }

    public void downAmount(int sum) {
        amount -= sum;
    }

    public static void threadOne() {
        Random random = new Random();
//        locker(lock1,lock2);
        lock1.lock();
        lock2.lock();
        for (int i = 0; i < 10000; i++) {
            Acount.transaction(acount1, acount2, random.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }

    public static void threadTwo() {
        Random random = new Random();
//        locker(lock2,lock1);
        lock2.lock();
        lock1.lock();
        for (int i = 0; i < 10000; i++) {
            Acount.transaction(acount2, acount1, random.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }

    public static void locker(Lock lock1, Lock lock2) {
        boolean one = false;
        boolean two = false;
        while (true) {
            one = lock1.tryLock();
            two = lock2.tryLock();

            if (one && two) {
                return;
            }
            if (one) {
                lock1.unlock();
            }
            if (two) {
                lock2.unlock();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void transaction(Acount acount1, Acount acount2, int sum) {
        acount1.downAmount(sum);
        acount2.upAmount(sum);
    }

    public static void getBalance(){
        System.out.println(acount1.amount);
        System.out.println(acount2.amount);
        System.out.println("All balance : " + (acount1.amount + acount2.amount));
    }
}