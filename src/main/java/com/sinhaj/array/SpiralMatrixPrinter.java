package com.sinhaj.array;

/**
 * Created by ajaysinha on 31/12/14.
 */
public class SpiralMatrixPrinter {

    public static void main(String[] args) {
        /*int [][]matrix = {{1}};*/
        int [][]matrix = {  {1,2},
                            {3,4}
                         };
        /*int [][]matrix = {  {1,2,3},
                            {4,5,6},
                            {7,8,9}
                        };*/
        /*int [][]matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };*/

        print(matrix);
    }

    public static void print(int [][]matrix) {
        int n = matrix.length;
        printInternal(matrix, 0, n);
    }

    private static void printInternal(int[][] matrix, int index, int length) {
        if(length <= 0) {
            return;
        }
        if(length == 1) {
            System.out.print(matrix[index][index]);
            return;
        }
        for(int i = index; i < index + length - 1; i++) {
            System.out.printf("%d, ", matrix[index][i]);
        }
        for(int i = index; i < index + length - 1; i++) {
            System.out.printf("%d, ", matrix[i][length + index - 1]);
        }
        for(int i = index + length - 1; i > index; i--) {
            System.out.printf("%d, ", matrix[length + index - 1][i]);
        }
        for(int i = index + length - 1; i > index; i--) {
            System.out.printf("%d, ", matrix[i][index]);
        }
        printInternal(matrix, index + 1, length - 2);
    }
}
