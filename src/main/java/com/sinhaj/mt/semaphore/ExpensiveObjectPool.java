package com.sinhaj.mt.semaphore;

import java.util.UUID;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

/**
 * Created by ajaysinha on 1/1/14.
 */
public class ExpensiveObjectPool {

    private BlockingDeque<ExpensiveObject> availablePool;
    private BlockingDeque<ExpensiveObject> allocatedPool;
    private Semaphore semaphore;

    public ExpensiveObjectPool(int poolSize) {
        availablePool = new LinkedBlockingDeque<ExpensiveObject>(poolSize);
        allocatedPool = new LinkedBlockingDeque<ExpensiveObject>(poolSize);
        semaphore = new Semaphore(poolSize);
        for(int i = 0; i < poolSize; i++) {
            availablePool.add(new ExpensiveObjectImpl());
        }
    }

    public ExpensiveObject getObject() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": Requested to get Expensive object..");
        semaphore.acquire();
        ExpensiveObject expensiveObject = null;
        try {
            expensiveObject = availablePool.take();
        } catch (InterruptedException e) {
            semaphore.release();
            throw e;
        }
        try {
            allocatedPool.put(expensiveObject);
        } catch (InterruptedException e) {
            semaphore.release();
            if(expensiveObject != null) {
                availablePool.put(expensiveObject);
            }
            throw e;
        }
        System.out.println(Thread.currentThread().getName() + ": Acquired Expensive object " + expensiveObject);
        return expensiveObject;
    }

    public void returnObject(ExpensiveObject expensiveObject) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": Returning expensive object " + expensiveObject);
        allocatedPool.remove(expensiveObject);
        availablePool.put(expensiveObject);
        System.out.println(Thread.currentThread().getName() + ": Returned expensive object " + expensiveObject);
        semaphore.release();
    }

    private class ExpensiveObjectImpl implements ExpensiveObject {
        private String guid;

        private ExpensiveObjectImpl() {
            guid = UUID.randomUUID().toString();
        }

        @Override
        public void doWork(String arg) {
            System.out.println(Thread.currentThread().getName() + ": Expensive object is doing some serious work..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": Expensive object completed it's work..");
        }

        @Override
        public void close() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ExpensiveObjectImpl that = (ExpensiveObjectImpl) o;

            if (!guid.equals(that.guid)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return guid.hashCode();
        }

        @Override
        public String toString() {
            return "ExpensiveObjectImpl{" +
                    "guid='" + guid + '\'' +
                    '}';
        }
    }
}
