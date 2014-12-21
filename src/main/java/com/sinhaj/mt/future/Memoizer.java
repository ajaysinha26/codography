package com.sinhaj.mt.future;

import java.util.concurrent.*;

/**
 * Created by ajaysinha on 12/29/13.
 */
public class Memoizer<K, V> implements Computable<K, V> {

    private ConcurrentHashMap<K, Future<V>> cache = new ConcurrentHashMap<K, Future<V>>();
    private Computable<K, V> computable;

    public Memoizer(Computable<K, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(final K arg) {
        Future<V> future = cache.get(arg);
        if(future == null) {
            FutureTask<V> futureTask = new FutureTask<V>(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return computable.compute(arg);
                }
                });
            future = futureTask;
            cache.put(arg, future);
            futureTask.run();
        }
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
