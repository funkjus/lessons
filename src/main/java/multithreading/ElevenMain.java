package multithreading;

import java.util.concurrent.*;

/**
 * Semaphore ()
 */
public class ElevenMain {
    public static void main(String[] args) throws InterruptedException {
        Connection connection = Connection.getConnection();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for(int i = 0; i < 100 ; i++){
            executorService.submit(new Thread(() -> {
                try {
                    connection.doWork();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
class Connection {
    private static Connection connection = new Connection();
    private int counter;
    private Connection(){}
    private Semaphore semaphore = new Semaphore(10);

    public static Connection getConnection(){
        return connection;
    }

    public void doWork() throws InterruptedException {
        semaphore.acquire();
        try{
            counter();
        } finally {
            semaphore.release();
        }
    }

    public  void counter() throws InterruptedException {
        synchronized (this) {
            counter++;
            System.out.println(counter);
        }

        Thread.sleep(5000);

        synchronized (this) {
            counter--;
        }
    }
}
