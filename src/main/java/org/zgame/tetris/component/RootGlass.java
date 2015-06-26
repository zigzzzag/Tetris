package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.utils.Constants;

import java.awt.Graphics2D;
import java.util.Arrays;

/**
 * Created by SBT-Nikiforov-MO on 28.05.2015.
 */
public class RootGlass {

    private static final Logger log = LoggerFactory.getLogger(RootGlass.class);
    private byte rowCount = Constants.matrY;
    private byte columnCount = Constants.matrX;
    private byte[][] filledGlass;

    public RootGlass() {
        filledGlass = new byte[rowCount][columnCount];
    }

    public RootGlass(byte[][] filledGlass) {
        this();
        this.filledGlass = filledGlass;
    }

    public void paintRootGlass(Graphics2D g2d) {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (this.getFilledGlass()[row][column] != 0) {
                    FigurePaint.gradientFigure(g2d, FigurePaint.lightColor(this.getFilledGlass()[row][column]), FigurePaint.darkColor(this.getFilledGlass()[row][column]),
                            FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row));
                }
            }
        }
    }

    public void deleteFullLine(int lineNumber) {
        for (int row = lineNumber - 1; row >= 0; row--) {
            for (int column = 0; column < Constants.matrX; column++) {
                filledGlass[row + 1][column] = filledGlass[row][column];
                filledGlass[row][column] = 0;
            }
        }
    }

    public Boolean verifyGameOver() {
        boolean over = false;
        for (int j = 2; j < Constants.matrX - 2; j++) {
            if (filledGlass[0][j] != 0) {
                over = true;
            }
        }

        return false;
    }

    public boolean hasIntersectionWithFigure(TemplateOfFigure tof) {
        for (byte row = 0; row < rowCount; row++) {
            for (byte column = 0; column < columnCount; column++) {
                if (tof.getFigure().getMatr()[row][column] != 0 && filledGlass[row][column] != 0) {
                    log.trace("RootGlass has intersection with TOF {}: (row, column) -> ({},{})", tof.getTypeOfFigure(), row, column);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(filledGlass);
    }

    public byte[][] getFilledGlass() {
        return filledGlass;
    }

    public void setFilledGlass(byte[][] filledGlass) {
        this.filledGlass = filledGlass;
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
