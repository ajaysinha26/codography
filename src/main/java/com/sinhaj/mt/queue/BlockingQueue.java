package com.sinhaj.mt.queue;

/**
 * Created by ajaysinha on 1/5/14.
 */
public class BlockingQueue<T> {
    private T[] buffer;
    private volatile int head;
    private volatile int tail;
    private volatile int count;

    public BlockingQueue(int bufferLength) {
        buffer = (T[]) new Object[bufferLength];
    }

    public synchronized void put(T entry) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        buffer[tail] = entry;
        tail++;
        if(tail == buffer.length) {
            tail = 0;
        }
        count++;
        notifyAll();
    }

    public synchronized T remove() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        T element = buffer[head];
        head++;
        if(head == buffer.length) {
            head = 0;
        }
        count--;
        notifyAll();
        return element;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    public synchronized boolean isFull() {
        return count == buffer.length;
    }
}
