package com.sinhaj.array;

/**
 * Created by ajaysinha on 21/12/14.
 */
public class MaxSubArraySumFinder {

    public static void main(String[] args) {
        int[] array = {1, 2, -4, 4, 1, 3, -9, 2, 3, 4};
        SubArrayIndex subArrayIndex = new MaxSubArraySumFinder().find(array);
        System.out.println(subArrayIndex);
    }

    public SubArrayIndex find(int []array) {
        SubArrayIndex[] idxArray = new SubArrayIndex[array.length];
        idxArray[0] = new SubArrayIndex();
        idxArray[0].start = idxArray[0].end = 0;
        idxArray[0].sum = array[0];
        for(int i = 1; i < array.length; i++) {
            idxArray[i] = new SubArrayIndex();
            if(idxArray[i - 1].sum > 0) {
                idxArray[i].start = idxArray[i - 1].start;
                idxArray[i].end = i;
                idxArray[i].sum = idxArray[i - 1].sum + array[i];
            } else {
                idxArray[i].start = idxArray[i].end = i;
                idxArray[i].sum = array[i];
            }
        }

        int maxSum = idxArray[0].sum;
        int maxIdxIndex = 0;
        for(int i = 1; i < idxArray.length; i++) {
            if(idxArray[i].sum > maxSum) {
                maxSum = idxArray[i].sum;
                maxIdxIndex = i;
            }
        }

        return idxArray[maxIdxIndex];
    }

    public static class SubArrayIndex {
        public int start;
        public int end;
        public int sum;

        @Override
        public String toString() {
            return "SubArrayIndex{" +
                    "start=" + start +
                    ", end=" + end +
                    ", sum=" + sum +
                    '}';
        }
    }
}
