package com.sinhaj.mt.semaphore;


import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ajaysinha on 12/28/13.
 */
public class BoundedSetTest {
    @Test
    public void testShouldAddAndRemoveElementsFromSet() throws Exception {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(6);
        BoundedSet<Integer> boundedSet = new BoundedSet<Integer>(300);
        new Thread(new ProducerConsumerThread(startGate, endGate, boundedSet, 0)).start();
        new Thread(new ProducerConsumerThread(startGate, endGate, boundedSet, 1)).start();
        new Thread(new ProducerConsumerThread(startGate, endGate, boundedSet, 1)).start();
        new Thread(new ProducerConsumerThread(startGate, endGate, boundedSet, 2)).start();
        new Thread(new ProducerConsumerThread(startGate, endGate, boundedSet, 0)).start();
        new Thread(new ProducerConsumerThread(startGate, endGate, boundedSet, 3)).start();
        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
        System.out.println("No of times add called " + boundedSet.addCount());
        System.out.println("No of times elements added " + boundedSet.elementsAddedCount());
        System.out.println("No of times remove called " + boundedSet.removeCount());
    }
}

class ProducerConsumerThread implements Runnable {

    private CountDownLatch startGate;
    private CountDownLatch endGate;
    private BoundedSet<Integer> boundedSet;
    private int seed;
    private int[] producedNumbers = new int[100];

    ProducerConsumerThread(CountDownLatch startGate, CountDownLatch endGate, BoundedSet<Integer> boundedSet, int seed) {
        this.startGate = startGate;
        this.endGate = endGate;
        this.boundedSet = boundedSet;
        this.seed = seed;
    }

    @Override
    public void run() {
        try {
            startGate.await();
            int base = 100 * seed;
            for(int i = 0; i < 100; i++) {
                int random = base + i;
                producedNumbers[i] = random;
                boundedSet.add(random);
            }

            for(int i = 0; i < 100; i++) {
                boundedSet.remove(producedNumbers[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            endGate.countDown();
        }
    }
}


