package com.sinhaj.stack;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 8/10/13
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueueUsingTwoStacks {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public QueueUsingTwoStacks() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public void enqueue(int element) {
        if(stack1.size() != stack1.capacity()) {
            stack1.push(element);
        }
        else if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                int pop = stack1.pop();
                stack2.push(pop);
            }
            stack1.push(element);
        }
    }

    public int dequeue() {
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                int pop = stack1.pop();
                stack2.push(pop);
            }
        }
        int pop = stack2.pop();
        return pop;
    }
}
