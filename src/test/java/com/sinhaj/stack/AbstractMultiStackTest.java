package com.sinhaj.stack;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/20/13
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractMultiStackTest {

    protected MultiStack multiStack;
    protected final static int NO_OF_STACKS = 3;
    protected final static int STACK_CAPACITY = 100;

    @Test
    public void testStacksAreEmptyWhenCreated() throws Exception {
        for(int i = 1; i <= NO_OF_STACKS; i++) {
            assertEquals(true, multiStack.isEmpty(i));
        }
    }

    @Test
    public void testStacksAreOfZeroSizeWhenCreated() throws Exception {
        for(int i = 1; i <= NO_OF_STACKS; i++) {
            assertEquals(0, multiStack.size(i));
        }
    }

    @Test
    public void testIncreaseInSizeWhenValuesAreInserted() throws Exception {
        for (int i = 1; i <= NO_OF_STACKS; i++) {
            multiStack.push(i, 10);
            multiStack.push(i, 15);
            assertEquals(2, multiStack.size(i));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowStackOverflowException() throws Exception {
        for(int i = 1; i <= NO_OF_STACKS; i++) {
            for(int j = 0; j < 100; j++) {
                multiStack.push(i, j);
            }
        }
        multiStack.push(3, 100);
    }

    @Test
    public void testShouldInsertFullSizeInStacks() throws Exception {
        for(int i = 1; i < NO_OF_STACKS; i++) {
            for(int j = 0; j < 100; j++) {
                multiStack.push(i, j);
            }
            assertEquals(100, multiStack.size(i));
        }
    }

    @Test
    public void testShouldPopTopElementOfStack() throws Exception {
        for(int i = 1; i < NO_OF_STACKS; i++) {
            for(int j = 0; j <= 10; j++) {
                multiStack.push(i, j);
            }
            assertEquals(10, multiStack.pop(i));
        }
    }

    @Test
    public void testShouldMakeStacksEmptyWhenPoppedCompletely() throws Exception {
        for(int i = 1; i < NO_OF_STACKS; i++) {
            multiStack.push(i, 5);
            multiStack.pop(i);
            assertEquals(true, multiStack.isEmpty(i));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testThrowStackEmptyExceptionWhenPopInvokedOnEmptyStack() throws Exception {
        multiStack.pop(1);
    }
}
