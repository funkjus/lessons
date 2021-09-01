package multithreading;

/**
 * synchronized on this.object
 * */

public class ThreeMain {
    private int counter;

    public static void main(String[] args) {
        ThreeMain threeMain = new ThreeMain();
        long start = System.currentTimeMillis();
        threeMain.doWork();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(threeMain.counter);
    }

    public synchronized void count() {
        counter++;
    }

    public void doWork() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count();
                }
            }
        });

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
