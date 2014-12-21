package com.sinhaj.mt.future;

/**
 * Created by ajaysinha on 12/29/13.
 */
public interface Computable<K, V> {
    V compute(K arg);
}
