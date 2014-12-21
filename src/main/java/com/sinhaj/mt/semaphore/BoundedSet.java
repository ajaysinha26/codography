package com.sinhaj.mt.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ajaysinha on 12/28/13.
 */
public class BoundedSet<V> {
    private Set<V> set;
    private Semaphore semaphore;
    private AtomicInteger noOfTimesAddCalled = new AtomicInteger(0);
    private AtomicInteger noOfTimesElementsAdded = new AtomicInteger(0);
    private AtomicInteger noOfTimesRemoveCalled = new AtomicInteger(0);

    public BoundedSet(int bound) {
        semaphore = new Semaphore(bound);
        set = Collections.synchronizedSet(new HashSet<V>());
    }

    public boolean add(V element) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is trying to acquire the semaphore to add element " + element);
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + " acquired the semaphore to add element " + element);
        noOfTimesAddCalled.incrementAndGet();
        boolean addedSuccessfully = false;
        try {
            addedSuccessfully = set.add(element);
            return addedSuccessfully;
        }
        finally {
            if(!addedSuccessfully) {
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " released the semaphore as it failed to add element " + element);
            }
            else {
                noOfTimesElementsAdded.incrementAndGet();
            }
        }
    }

    public boolean remove(V element) {
        noOfTimesRemoveCalled.incrementAndGet();
        boolean removedSuccessfully = set.remove(element);
        if(removedSuccessfully) {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " released the semaphore as it removed element " + element);
        }
        return removedSuccessfully;
    }

    public int addCount() {
        return noOfTimesAddCalled.get();
    }

    public int removeCount() {
        return noOfTimesRemoveCalled.get();
    }

    public int elementsAddedCount() {
        return noOfTimesElementsAdded.get();
    }
}
