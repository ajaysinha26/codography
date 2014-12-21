package com.sinhaj.mt.queue;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ajaysinha on 1/5/14.
 */
public class BlockingQueueTest {

    @Test
    public void testShouldAddElementsWhenQueueIsEmpty() throws Exception {
        BlockingQueue queue = new BlockingQueue(10);
        /*ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Consumer(queue));
        executorService.submit(new Producer(queue));
        executorService.shutdown();*/
        Thread t1 = new Thread(new Consumer(queue));
        t1.start();
        Thread t2 = new Thread(new Producer(queue));
        t2.start();
        t1.join();
        t2.join();
    }

    class Producer implements Runnable {

        private BlockingQueue<Integer> queue;

        Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for(int i = 0; i < 20; i++) {
                try {
                    System.out.println("Producer pushing item: " + i);
                    queue.put(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class Consumer implements Runnable {

        private BlockingQueue<Integer> queue;

        Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for(int i = 0; i < 20; i++) {
                try {
                    Integer item = queue.remove();
                    System.out.println("Consumer retrieved item: " + item);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
