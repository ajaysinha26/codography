package com.sinhaj.examples;

/**
 * Created by ajaysinha on 2/25/14.
 */
public class ExceptionInTryBlock {
    public static void main(String[] args) {
        try {
            System.out.println("throwing exception");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("throwing runtime exception");
            throw new RuntimeException();
        } finally {
            System.out.println("Finally");
        }

    }
}
