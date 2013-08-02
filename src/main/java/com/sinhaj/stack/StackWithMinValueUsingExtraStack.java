package com.sinhaj.stack;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/21/13
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class StackWithMinValueUsingExtraStack implements StackWithMinValue{
    private Stack<Integer> stack;
    private Stack<Integer> minValueStack;

    public StackWithMinValueUsingExtraStack() {
        stack = new Stack<Integer>();
        minValueStack = new Stack<Integer>();
    }

    @Override
    public void push(int value) {
        if(value < min()) {
            minValueStack.push(value);
        }
        stack.push(value);
    }

    @Override
    public int pop() {
        int poppedValue = stack.pop();
        if(poppedValue == min()) {
            minValueStack.pop();
        }
        return poppedValue;
    }

    @Override
    public int min() {
        if(minValueStack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        else {
            return minValueStack.peek();
        }
    }
}
