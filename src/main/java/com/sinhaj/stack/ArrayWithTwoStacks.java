package com.sinhaj.stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 8/4/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayWithTwoStacks {

    private int stackContainer[];
    private int size;
    private int firstStackTop;
    private int secondStackTop;

    public ArrayWithTwoStacks(int size) {
        stackContainer = new int[size];
        this.size = size;
        firstStackTop = -1;
        secondStackTop = size;
    }


    public boolean isEmpty(int stackNumber) {
        if(stackNumber == 1) {
            return firstStackTop == -1;
        }
        else if(stackNumber == 2) {
            return secondStackTop == size;
        }
        throw new RuntimeException("Unknown stack number");
    }

    public void push(int stackNumber, int element) {
        if(stackNumber == 1) {
            firstStackTop++;
            if(firstStackTop == secondStackTop) {
                throw new RuntimeException("Stack overflow");
            }
            stackContainer[firstStackTop] = element;
        }
        else if(stackNumber == 2) {
            secondStackTop--;
            if(secondStackTop == firstStackTop) {
                throw new RuntimeException("Stack overflow");
            }
            stackContainer[secondStackTop] = element;
        }
        else {
            throw new RuntimeException("Unknown stack number");
        }
    }

    public int pop(int stackNumber) {
        if(isEmpty(stackNumber)) {
            throw new RuntimeException("Stack empty");
        }
        if(stackNumber == 1) {
            int pop = stackContainer[firstStackTop];
            firstStackTop--;
            return pop;
        }
        else if(stackNumber == 2) {
            int pop = stackContainer[secondStackTop];
            secondStackTop++;
            return pop;
        }
        else {
            throw new RuntimeException("Unknown stack number");
        }
    }
}
