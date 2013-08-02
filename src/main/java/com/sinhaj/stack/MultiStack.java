package com.sinhaj.stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/21/13
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MultiStack {
    boolean isEmpty(int i);

    int size(int i);

    void push(int stackNumber, int value);

    int pop(int stackNumber);
}
