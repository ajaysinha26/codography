package com.sinhaj.array;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 9/2/13
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Image {

    private char[][] matrix;

    public Image(char[][] matrix) {
        this.matrix = matrix;
    }

    public void rotate() {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        char[][] thatMatrix = ((Image)o).matrix;
        if(matrix.length != thatMatrix.length) {
            return false;
        }
        for(int i = 0; i < matrix.length; i++) {
            char []row = matrix[i];
            char []thatRow = thatMatrix[i];
            if(!Arrays.equals(row, thatRow)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
