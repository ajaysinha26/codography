package com.sinhaj.bitwise;

/**
 * Created by ajaysinha on 21/12/14.
 */
public class PowerSetGenerator {

    public static void main(String[] args) {
        int []set = {1, 2, 3, 4};
        generate(set);
    }

    private static void generate(int[] set) {
        int length = (int) (Math.pow(2, set.length) - 1);
        for(int i = 1; i <= length; i++) {
            System.out.println();
            int x = i;
            while(x != 0) {
                int idx = setBitPosition(x & ~(x - 1));
                System.out.print(set[idx]);
                x = x & (x - 1);
                if(x != 0) {
                    System.out.print(", ");
                }
            }
        }
    }

    private static int setBitPosition(int x) {
        int pos = -1;
        while(x != 0){
            x >>= 1;
            pos++;
        }
        return pos;
    }
}
