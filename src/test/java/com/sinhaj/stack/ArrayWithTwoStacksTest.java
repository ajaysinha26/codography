package com.sinhaj.stack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 8/4/13
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayWithTwoStacksTest {

    public static final int SIZE = 100;
    private ArrayWithTwoStacks arrayWithTwoStacks;

    @Before
    public void setUp() throws Exception {
        arrayWithTwoStacks = new ArrayWithTwoStacks(SIZE);
    }

    @Test
    public void testBothStacksAreEmpty() throws Exception {
        assertEquals(true, arrayWithTwoStacks.isEmpty(1));
        assertEquals(true, arrayWithTwoStacks.isEmpty(2));
    }

    @Test
    public void testShouldPushInFirstStack() throws Exception {
        arrayWithTwoStacks.push(1, 10);
        assertEquals(false, arrayWithTwoStacks.isEmpty(1));
        assertEquals(true, arrayWithTwoStacks.isEmpty(2));
    }

    @Test
    public void testShouldPushInSecondStack() throws Exception {
        arrayWithTwoStacks.push(2, 10);
        assertEquals(true, arrayWithTwoStacks.isEmpty(1));
        assertEquals(false, arrayWithTwoStacks.isEmpty(2));
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowStackOverflowExceptionWhenCrossingOtherStackTop() throws Exception {
        for(int i = 1; i <= 51; i++) {
            arrayWithTwoStacks.push(1, i);
        }
        for(int i = 1; i <= 50; i++) {
            arrayWithTwoStacks.push(2, i);
        }
    }

    @Test
    public void testShouldPopFromFirstStack() throws Exception {
        arrayWithTwoStacks.push(1, 10);
        int pop = arrayWithTwoStacks.pop(1);
        assertEquals(10, pop);
        assertEquals(true, arrayWithTwoStacks.isEmpty(1));
    }

    @Test
    public void testShouldPopFromSecondStack() throws Exception {
        arrayWithTwoStacks.push(2, 10);
        int pop = arrayWithTwoStacks.pop(2);
        assertEquals(10, pop);
        assertEquals(true, arrayWithTwoStacks.isEmpty(2));
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowExceptionIFGetPopOperationInFirstStackInEmptyState() throws Exception {
        assertEquals(true, arrayWithTwoStacks.isEmpty(1));
        arrayWithTwoStacks.pop(1);
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowExceptionIFGetPopOperationInSecondStackInEmptyState() throws Exception {
        assertEquals(true, arrayWithTwoStacks.isEmpty(2));
        arrayWithTwoStacks.pop(2);
    }

}
