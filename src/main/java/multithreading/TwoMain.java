package multithreading;

import java.util.Scanner;

/**
 * volatile
 * */

public class TwoMain {
    public static void main(String[] args) {
        TwoThread twoThread = new TwoThread();
        twoThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        twoThread.closer = true;
        System.out.println("Closer");

    }
}

class TwoThread extends Thread {
    public volatile boolean closer = false;
    public void run() {
        while (!closer) {
            System.out.println("TwoThread BOOM!!");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e){

            }
        }
    }
}
