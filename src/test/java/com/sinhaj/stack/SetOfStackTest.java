package com.sinhaj.stack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/30/13
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SetOfStackTest {

    private SetOfStack setOfStack;

    @Before
    public void setUp() throws Exception {
        setOfStack = new SetOfStack();
    }

    @Test
    public void testShouldCheckForEmptyStack() throws Exception {
        assertEquals(true, setOfStack.isEmpty());
    }

    @Test
    public void testShouldInsertElementsInStack() throws Exception {
        setOfStack.push(1);
        assertEquals(false, setOfStack.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowStackOverflowException() throws Exception {
        for(int i = 0; i <= 1000; i++) {
            setOfStack.push(i);
        }
    }

    @Test
    public void testShouldMakeStackEmptyWithPop() throws Exception {
        setOfStack.push(1);
        int pop = setOfStack.pop();
        assertEquals(true, setOfStack.isEmpty());
    }

    @Test
    public void testShouldPushElementsToFullCapacityOfStackAndPopThemBack() throws Exception {
        for(int i = 1; i <= 1000; i++) {
            setOfStack.push(i);
        }
        for(int i = 1000; i >= 1; i--) {
            int pop = setOfStack.pop();
            assertEquals(i, pop);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowStackEmptyException() throws Exception {
        setOfStack.pop();
    }
}
