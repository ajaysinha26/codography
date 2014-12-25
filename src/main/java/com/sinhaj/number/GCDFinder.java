package com.sinhaj.number;

/**
 * Created by ajaysinha on 25/12/14.
 */
public class GCDFinder {

    public static void main(String[] args) {
        int [][]numbers = {{1, 2}, {18, 45}, {24, 1024}, {37, 91}};
        for(int row = 0; row < numbers.length; row++) {
            int x = numbers[row][0];
            int y = numbers[row][1];
            System.out.println("GCD of " + x + " and " + y + " is " + findGCD(x, y));
        }
    }

    public static int findGCD(int x, int y) {
        if(x == 0) {
            return y;
        } else if(y == 0) {
            return x;
        } else if(((x & 1) == 0) && ((y & 1) == 0)) { // both x and y are even
            return (findGCD(x >> 1, y >> 1) << 1);
        } else if(((x & 1) == 0) && ((y & 1) != 0)) { // x is even and y is odd
            return findGCD(x >> 1, y);
        } else if(((x & 1) != 0) && ((y & 1) == 0)) { // x is odd and y is even
            return findGCD(x, y >> 1);
        } else if(x >= y) { // both x and y are odd and x >= y
            return findGCD(x - y, y);
        } else { // both x and y are odd and x < y
            return findGCD(x, y - x);
        }
    }
}
