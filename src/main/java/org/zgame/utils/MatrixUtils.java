package org.zgame.utils;

/**
 * Created by mnikiforov on 07.06.2015.
 */
public class MatrixUtils {

    public static byte[][] transposeMatrixCounterClockWise(byte[][] matr, int rowCount, int columnCount) {
        byte[][] result = new byte[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                result[columnCount - column - 1][row] = matr[row][column];
            }
        }
        return result;
    }

    public static byte[][] transposeMatrixClockWise(byte[][] matr, int rowCount, int columnCount) {
        byte[][] result = new byte[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                result[column][rowCount - row - 1] = matr[row][column];
            }
        }
        return result;
    }
}
