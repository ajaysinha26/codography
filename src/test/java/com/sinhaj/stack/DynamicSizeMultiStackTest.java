package com.sinhaj.stack;

import org.junit.Before;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 7/21/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynamicSizeMultiStackTest extends AbstractMultiStackTest {

    @Before
    public void setUp() throws Exception {
        multiStack = new DynamicSizeMultiStack(NO_OF_STACKS, STACK_CAPACITY);
    }
}
