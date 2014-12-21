package com.sinhaj.linkedlist;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 10/28/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListTest {

    @Test
    public void testShouldAddDataAndRetrieveToLinkedList() throws Exception {
        int []dataArray = {1, 3, 5, 2, 7, 4};
        LinkedList linkedList = new LinkedList();
        for (int aDataArray : dataArray) {
            linkedList.add(aDataArray);
        }
        int i = 0;
        for (int aDataArray : dataArray) {
            assertEquals(aDataArray, linkedList.get(i++));
        }
    }
}
