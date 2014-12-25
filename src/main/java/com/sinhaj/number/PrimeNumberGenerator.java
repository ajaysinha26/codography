package com.sinhaj.number;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by ajaysinha on 25/12/14.
 */
public class PrimeNumberGenerator {

    public static void main(String[] args) {

        long start2 = System.currentTimeMillis();
        List<Integer> result2 = generate1(30000);
        long end2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (end2 - start2) + " ms");
        System.out.println(result2);

        long start1 = System.currentTimeMillis();
        List<Integer> result1 = generate1(30000);
        long end1 = System.currentTimeMillis();
        System.out.println("Time taken: " + (end1 - start1) + " ms");
        System.out.println(result1);
    }

    public static List<Integer> generate1(int n) {
        BitSet bitSet = new BitSet(n + 1);
        bitSet.set(0, n);
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 2; i <= n + 2; i++) {
            if(bitSet.get(i - 1)) {
                result.add(i);
                for(int j = i * i; j <= n + 2; j += i) {
                    bitSet.clear(j - 1);
                }
            }
        }
        return result;
    }

    public static List<Integer> generate2(int n) {
        int length = (n - 3) / 2 + 2;
        BitSet bitSet = new BitSet(length);
        bitSet.set(0, length - 1);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(2);
        for(int i = 0; i < length; i++) {
            if(bitSet.get(i)) {
                int p = (i << 2) + 3;
                result.add(p);
                for(int j =  ((i * i) << 2) + (6 * i) + 3; j < n; j += p) {
                    bitSet.clear(j);
                }
            }
        }
        return result;
    }
}
