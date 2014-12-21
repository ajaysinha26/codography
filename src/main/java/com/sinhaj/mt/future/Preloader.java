package com.sinhaj.mt.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by ajaysinha on 12/28/13.
 */
public class Preloader {
    private FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return load();
            }
        });

    private Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public Integer get() {
        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isLoadDone() {
        return futureTask.isDone();
    }

    private Integer load() {
        System.out.println("I will take time to load..");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Load is done, returning the value now");
        return 1;
    }

    public static void main(String args[]) {
        Preloader preloader = new Preloader();
        preloader.start();
        System.out.println("Is load done: " + preloader.isLoadDone());
        System.out.println("Loaded value: " + preloader.get());
        System.out.println("Is load done: " + preloader.isLoadDone());
    }
}
