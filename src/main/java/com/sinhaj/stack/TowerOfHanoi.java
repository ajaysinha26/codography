package com.sinhaj.stack;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 8/9/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TowerOfHanoi {

    public void move(char []disks, int startIndex, int endIndex, String source, String target, String intermediate) {
        int n = endIndex - startIndex;
        if(n < 0) {
            return;
        }
        else if(n == 0) {
            System.out.println("Move disk " + disks[startIndex] + " from " + source + " to " + target);
        }
        else if(n == 1) {
            System.out.println("Move disk " + disks[endIndex] + " from " + source + " to " + intermediate);
            System.out.println("Move disk " + disks[startIndex] + " from " + source + " to " + target);
            System.out.println("Move disk " + disks[endIndex] + " from " + intermediate + " to " + target);
        }
        else {
            move(disks, startIndex + 1, endIndex, source, intermediate, target);
            move(disks, startIndex, startIndex, source, target, intermediate);
            move(disks, startIndex + 1, endIndex, intermediate, target, source);
        }

    }

}
