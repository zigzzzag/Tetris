package org.zgame.tetris.component.matr;

/**
 * Created by SBT-Nikiforov-MO on 25.06.2015.
 */
public class Matr {

    private byte[][] matr;
    private byte rowCount;
    private byte columnCount;

    public Matr(byte rowCount, byte columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.matr = new byte[rowCount][columnCount];
    }

    public byte getElement(int row, int column) {
        return this.matr[row][column];
    }

    public void setElement(int value, int row, int column) {
        this.matr[row][column] = (byte) value;
    }

    public void setAllNotNullElements(byte value) {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (matr[row][column] != 0) {
                    matr[row][column] = value;
                }
            }
        }
    }

    public void clear() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                matr[row][column] = 0;
            }
        }
    }

    public void transposeMatrCounterClockWise() {
        byte[][] result = new byte[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                result[columnCount - column - 1][row] = matr[row][column];
            }
        }
        this.matr = result;
    }

    public void transposeMatrixClockWise() {
        byte[][] result = new byte[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                result[column][rowCount - row - 1] = matr[row][column];
            }
        }
        this.matr = result;
    }

    public byte[][] getMatr() {
        return matr;
    }

    public void setMatr(byte[][] matr) {
        this.matr = matr;
    }

    public byte getRowCount() {
        return rowCount;
    }

    public void setRowCount(byte rowCount) {
        this.rowCount = rowCount;
    }

    public byte getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(byte columnCount) {
        this.columnCount = columnCount;
    }
}
