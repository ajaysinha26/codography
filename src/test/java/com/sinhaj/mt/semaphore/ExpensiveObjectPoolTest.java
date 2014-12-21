package com.sinhaj.mt.semaphore;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ajaysinha on 1/1/14.
 */
public class ExpensiveObjectPoolTest {

    @Test
    public void testShouldGetAndReturnObjectsFromPool() throws Exception {
        final ExpensiveObjectPool objectPool = new ExpensiveObjectPool(2);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(int i = 0; i < 4; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        ExpensiveObject object = objectPool.getObject();
                        object.doWork("SomeWork");
                        objectPool.returnObject(object);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(task);
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
