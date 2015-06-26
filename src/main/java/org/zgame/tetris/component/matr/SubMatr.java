package org.zgame.tetris.component.matr;

/**
 * Created by SBT-Nikiforov-MO on 25.06.2015.
 */
public class SubMatr extends Matr {

    private int rowCoord;
    private int columnCoord;

    public SubMatr(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public SubMatr rowCoord(int rowCoord) {
        this.rowCoord = rowCoord;
        return this;
    }

    public SubMatr columnCoord(int columnCoord) {
        this.columnCoord = columnCoord;
        return this;
    }

    public void incrementRow() {
        this.rowCoord++;
    }

    public void decrementRow() {
        this.rowCoord--;
    }

    public void incrementColumn() {
        this.columnCoord++;
    }

    public void decrementColumn() {
        this.columnCoord--;
    }

    public void update(Matr matrix) {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                this.matr[row][column] = matrix.getMatr()[rowCoord + row][columnCoord + column];
            }
        }
    }

    public void transposeMatrCounterClockWise() {
        byte[][] result = new byte[columnCount][rowCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                result[columnCount - column - 1][row] = matr[row][column];
            }
        }
        updateSize(columnCount, rowCount);
        this.matr = result;
    }

    public void transposeMatrClockWise() {
        byte[][] result = new byte[columnCount][rowCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                result[column][rowCount - row - 1] = matr[row][column];
            }
        }
        updateSize(columnCount, rowCount);
        this.matr = result;
    }

    private void updateSize(int newRowCount, int newColumnCount) {
        setRowCount(newRowCount);
        setColumnCount(newColumnCount);
    }

    public int getRowCoord() {
        return rowCoord;
    }

    public void setRowCoord(int rowCoord) {
        this.rowCoord = rowCoord;
    }

    public int getColumnCoord() {
        return columnCoord;
    }

    public void setColumnCoord(int columnCoord) {
        this.columnCoord = columnCoord;
    }
}
