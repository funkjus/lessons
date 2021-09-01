package multithreading;

/**
 * interface Runnable (implements)
 * and class Thread (extends)
 * */

public class OneMain {
    public static void main(String[] args) throws InterruptedException {
        OneMain oneMain = new OneMain();
        System.out.println("Main start......");
        oneMain.doWork();
        Thread.sleep(3000);
        System.out.println("EXIT");
    }

    public void doWork() {
        OneRunner runner = new OneRunner();
        OneThread myThread = new OneThread();
        Thread m = new Thread(runner);
        myThread.start();
        m.start();
    }
}

class OneRunner implements Runnable {

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner running");
        }
    }

}

class OneThread extends Thread {

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread threading");
        }
    }
}



