package com.sinhaj.stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/30/13
 * Time: 7:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class SetOfStack {
    private static final int THRESHOLD = 100;
    private static final int NO_OF_STACKS = 10;

    private int[][] stacks;
    private int currentStackCounter;
    private int currentStackTop;

    public SetOfStack() {
        stacks = new int[NO_OF_STACKS][THRESHOLD];
        currentStackCounter = 0;
        currentStackTop = -1;
    }

    public boolean isEmpty() {
        return (currentStackCounter == 0 && currentStackTop == -1);
    }

    public void push(int element) {
        if(currentStackCounter == (NO_OF_STACKS - 1) && currentStackTop == (THRESHOLD - 1)) {
            throw new RuntimeException("Stack overflow!");
        }
        if(currentStackTop == (THRESHOLD - 1)) {
            currentStackCounter++;
            currentStackTop = -1;
        }
        int []currentStack = stacks[currentStackCounter];
        currentStackTop++;
        currentStack[currentStackTop] = element;
    }

    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        int []currentStack = stacks[currentStackCounter];
        int poppedElement = currentStack[currentStackTop];
        currentStackTop--;
        if(currentStackTop == -1 && currentStackCounter > 0) {
            currentStackCounter--;
            currentStackTop = THRESHOLD - 1;
        }
        return poppedElement;
    }
}
