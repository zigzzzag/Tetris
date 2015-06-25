package org.zgame.tetris.component.matr;

/**
 * Created by SBT-Nikiforov-MO on 25.06.2015.
 */
public class Matr {

    protected byte[][] matr;
    protected int rowCount;
    protected int columnCount;

    public Matr(int rowCount, int columnCount) {
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

    public void copySubMatr(SubMatr subMatr) {
        clear();
        for (int row = 0; row < subMatr.getRowCount(); row++) {
            for (int column = 0; column < subMatr.getColumnCount(); column++) {
                matr[subMatr.getRowCoord() + row][subMatr.getColumnCoord() + column] = subMatr.getElement(row, column);
            }
        }
    }

    public byte[][] getMatr() {
        return matr;
    }

    public void setMatr(byte[][] matr) {
        this.matr = matr;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
