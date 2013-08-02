package com.sinhaj.stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/21/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynamicSizeMultiStack implements MultiStack {
    private final int noOfStacks;
    private final int stackCapacity;
    private StackNode []stackContainer;
    private int stackContainerIndex;
    private int []stackTop;
    private int []stackSize;

    public DynamicSizeMultiStack(int noOfStacks, int stackCapacity) {
        this.noOfStacks = noOfStacks;
        this.stackCapacity = stackCapacity;
        stackContainer = new StackNode[noOfStacks * stackCapacity];
        stackContainerIndex = -1;
        stackTop = new int[noOfStacks];
        stackSize = new int[noOfStacks];
        for(int i = 0; i < noOfStacks; i++) {
            stackTop[i] = -1;
            stackSize[i] = 0;
        }
    }

    @Override
    public boolean isEmpty(int stackNumber) {
        return stackTop[stackNumber - 1] == -1;
    }

    @Override
    public int size(int stackNumber) {
        return stackSize[stackNumber - 1];
    }

    @Override
    public void push(int stackNumber, int value) {
        if(stackContainerIndex >= noOfStacks * stackCapacity) {
            throw new RuntimeException("Stack overflow!");
        }
        StackNode stackNode = new StackNode(value, stackTop[stackNumber - 1]);
        stackContainerIndex++;
        stackContainer[stackContainerIndex] = stackNode;
        stackTop[stackNumber - 1] = stackContainerIndex;
        stackSize[stackNumber - 1]++;
    }

    @Override
    public int pop(int stackNumber) {
        if(isEmpty(stackNumber)) {
            throw new RuntimeException("Stack empty!");
        }
        StackNode poppedNode = stackContainer[stackTop[stackNumber - 1]];
        stackTop[stackNumber - 1] = poppedNode.previousNodeIndex;
        stackSize[stackNumber - 1]--;
        return poppedNode.value;
    }

    class StackNode {
        int value;
        int previousNodeIndex;

        StackNode(int value, int previousNodeIndex) {
            this.value = value;
            this.previousNodeIndex = previousNodeIndex;
        }
    }
}
