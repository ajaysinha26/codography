package com.sinhaj.mt.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ajaysinha on 1/5/14.
 */
public class ReadWriteMap<K, V> {
    private Map<K, V> map = new HashMap<K, V>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void put(K key, V value) {
        System.out.println(Thread.currentThread().getName() + " is requesting for write lock");
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " acquired write lock");
        try {
            map.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released write lock");
        }
    }

    public V get(K key) {
        System.out.println(Thread.currentThread().getName() + " is requesting for read lock");
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + " acquired read lock");
        V value = null;
        try {
            value = map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released read lock");
        }
        return value;
    }
}
