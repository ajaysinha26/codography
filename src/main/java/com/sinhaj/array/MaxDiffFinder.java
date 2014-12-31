package com.sinhaj.array;

/**
 * Created by ajaysinha on 30/12/14.
 */
public class MaxDiffFinder {

    public static class BuySell {
        public int buyPrice;
        public int sellPrice;

        @Override
        public String toString() {
            return "BuySell{" +
                    "buyPrice=" + buyPrice +
                    ", sellPrice=" + sellPrice +
                    ", profit=" + (sellPrice - buyPrice) +
                    '}';
        }
    }

    public static BuySell find(int[] array) {
        BuySell result = new BuySell();
        int bestBuySoFar = array[0];
        result.buyPrice = bestBuySoFar;
        result.sellPrice = array[1];
        int maxProfit = array[1] - array[0];
        for(int i = 1; i < array.length; i++) {
            if(array[i] - bestBuySoFar > maxProfit) {
                maxProfit = array[i] - bestBuySoFar;
                result.sellPrice = array[i];
                result.buyPrice = bestBuySoFar;
            }
            if(array[i] < bestBuySoFar) {
                bestBuySoFar = array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int array[] = {4, 2, 11, 3, 34, 1, 9, 40};
        System.out.printf("%s", find(array));
    }
}
