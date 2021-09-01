package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * synchronized on two object lock1 and lock2
 * */

public class ForMain {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.main();
        System.out.println(worker.list1.size());
        System.out.println(worker.list2.size());

    }
}

class Worker {
    Random random = new Random();

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    Object lock1 = new Object();
    Object lock2 = new Object();


    public void addList1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void addList2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public void work() {
        for (int i = 0; i < 1000; i++) {
            addList1();
            addList2();
        }
    }

    public void main() {
        Thread one = new Thread(() -> work());
        Thread two = new Thread(() -> work());

        long start = System.currentTimeMillis();

        one.start();
        two.start();

        try {
            one.join();
            two.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    };
}