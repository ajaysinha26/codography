package com.sinhaj.stack;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/21/13
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class StackWithMinValueUsingExtraNode implements StackWithMinValue {
    private Stack<StackNode> stack;

    public StackWithMinValueUsingExtraNode() {
        this.stack = new Stack<StackNode>();
    }

    @Override
    public void push(int value) {
        int newMin = (value > min()) ? min() : value;
        stack.push(new StackNode(value, newMin));
    }

    @Override
    public int pop() {
        return stack.pop().value;
    }

    @Override
    public int min() {
        if(stack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        else {
            return stack.peek().minValue;
        }
    }

    class StackNode {
        int value;
        int minValue;

        private StackNode(int value, int minValue) {
            this.value = value;
            this.minValue = minValue;
        }

        @Override
        public String toString() {
            return "StackNode{" +
                    "value=" + value +
                    ", minValue=" + minValue +
                    '}';
        }
    }
}
