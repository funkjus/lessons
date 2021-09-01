package multithreading;

/**
 *  producer consumer with
 *  wait
 *  notify
 */
public class SevenMain {
    public static void main(String[] args) {
        SevenMain m = new SevenMain();
        Thread thread1 = new Thread(()-> m.producer());
        Thread thread2 = new Thread(()-> m.consumer());

        thread1.start();
        thread2.start();

    }
    public int id;
    public synchronized void producer () {
        System.out.println("Producer start...." + id);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".........Producer end." + id);

    }
    public synchronized void consumer() {

        System.out.println("Consumer start...." + id);
        notify();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".........Consumer end." + id);
    }
}
