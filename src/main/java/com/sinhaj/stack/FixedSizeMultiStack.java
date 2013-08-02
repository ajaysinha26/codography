package com.sinhaj.stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/20/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class FixedSizeMultiStack implements MultiStack {

    private int [] stackContainer;
    private int []stackTop;
    private final int noOfStacks;
    private final int stackCapacity;

    public FixedSizeMultiStack(int noOfStacks, int stackCapacity) {
        this.noOfStacks = noOfStacks;
        this.stackCapacity = stackCapacity;
        stackContainer = new int[noOfStacks * stackCapacity];
        stackTop = new int[noOfStacks];
        for(int i = 0; i < noOfStacks; i++) {
            stackTop[i] = (i * stackCapacity) -1;
        }
    }

    @Override
    public boolean isEmpty(int i) {
        return stackTop[i - 1] == ((i - 1) * stackCapacity) -1;
    }

    @Override
    public int size(int i) {
        return stackTop[i - 1] - ((i - 1) * stackCapacity) + 1;
    }

    @Override
    public void push(int stackNumber, int value) {
        int insertionIndex = stackTop[stackNumber - 1] + 1;
        int maxInsertionIndex = (stackNumber * stackCapacity) - 1;
        if(insertionIndex > maxInsertionIndex) {
            throw new RuntimeException("Stack overflow!");
        }
        stackContainer[insertionIndex] = value;
        stackTop[stackNumber - 1]++;
    }

    @Override
    public int pop(int stackNumber) {
        if(isEmpty(stackNumber))
        {
            throw new RuntimeException("Empty stack!");
        }
        int popValue = stackContainer[stackTop[stackNumber - 1]];
        stackTop[stackNumber - 1]--;
        return popValue;
    }
}
