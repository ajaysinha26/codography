package com.sinhaj.stack;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 8/9/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TowerOfHanoiTest {

    @Test
    public void testMove() throws Exception {
        TowerOfHanoi towerOfHanoi = new TowerOfHanoi();
        char []disks = {'A', 'B', 'C', 'D', 'E'};
        towerOfHanoi.move(disks, 0, 4, "Source", "Target", "Intermediate");
    }
}
