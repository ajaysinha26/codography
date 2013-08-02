package com.sinhaj.stack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/21/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStackWithMinValueTest {

    protected StackWithMinValue stackWithMinValue;

    @Test
    public void testShouldGetMinValueFromStack() throws Exception {
        stackWithMinValue.push(8);
        stackWithMinValue.push(2);
        stackWithMinValue.push(5);
        stackWithMinValue.push(3);
        assertEquals(2, stackWithMinValue.min());
        stackWithMinValue.pop();
        assertEquals(2, stackWithMinValue.min());
    }
}
