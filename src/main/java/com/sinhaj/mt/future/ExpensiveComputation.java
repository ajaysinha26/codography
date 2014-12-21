package com.sinhaj.mt.future;

/**
 * Created by ajaysinha on 12/29/13.
 */
public class ExpensiveComputation implements Computable<Integer, Integer> {
    @Override
    public Integer compute(Integer arg) {
        System.out.println(Thread.currentThread().getName() + " requested to compute arg " + arg + ". I will take time to compute the value");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int computedValue = arg * 10;
        System.out.println(Thread.currentThread().getName() + " computed value is " + computedValue);
        return computedValue;
    }
}
