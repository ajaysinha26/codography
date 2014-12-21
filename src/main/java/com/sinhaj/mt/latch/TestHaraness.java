package com.sinhaj.mt.latch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ajaysinha on 12/27/13.
 */
public class TestHaraness {

    private static final int NO_OF_THREADS = 100;
    private CountDownLatch startGate = new CountDownLatch(1);
    private CountDownLatch endGate = new CountDownLatch(NO_OF_THREADS);

    public static void main(String args[]) throws InterruptedException {
        new TestHaraness().run();
    }

    public void run() throws InterruptedException {
        for(int i = 0; i < NO_OF_THREADS; i++) {
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    try {
                        startGate.await();
                        executeTask();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        endGate.countDown();
                    }
                }
            }   ;

            new Thread(runnable).start();
        }

        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime);
        System.out.println("Time taken to execute the task concurrently with " + NO_OF_THREADS + " threads is " + timeTaken);
    }

    private void executeTask() {
        long sum = 0;
        for(int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " calculated sum as " + sum);
    }
}
