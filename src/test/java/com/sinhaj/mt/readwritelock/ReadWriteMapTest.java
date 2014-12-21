package com.sinhaj.mt.readwritelock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ajaysinha on 1/5/14.
 */
public class ReadWriteMapTest {
    private final ReadWriteMap<Integer, Integer> readWriteMap = new ReadWriteMap<Integer, Integer>();

    @Test
    public void testShouldPutAndGetFromMapConcurrently() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i = 1; i <= 5; i++) {
            final int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    readWriteMap.put(finalI, finalI);
                }
            });
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " got value " + readWriteMap.get(finalI) + " for key " + finalI);
                }
            });
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " got value " + readWriteMap.get(finalI) + " for key " + finalI);
                }
            });
        }
        countDownLatch.countDown();
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

    }
}
