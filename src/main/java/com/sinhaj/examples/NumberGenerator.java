package com.sinhaj.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ajaysinha on 1/23/14.
 */
public class NumberGenerator {
    private static int maxNum = 100;
    private static boolean even = true;
    private static Object mutext = new Object();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new EvenNumberGenerator());
        executorService.submit(new OddNumberGenerator());
        //executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    static class EvenNumberGenerator implements Runnable {
        private int counter = 0;

        @Override
        public void run() {
            while (counter <= maxNum) {
                synchronized (mutext) {
                    while (!even) {
                        try {
                            mutext.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + counter);
                    counter = counter + 2;
                    even = false;
                    mutext.notifyAll();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": is done");
        }
    }

    static class OddNumberGenerator implements Runnable {
        private int counter = 1;

        @Override
        public void run() {
            while (counter <= maxNum) {
                synchronized (mutext) {
                    while (even) {
                        try {
                            mutext.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + counter);
                    counter = counter + 2;
                    even = true;
                    mutext.notifyAll();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": is done");
        }
    }
}
