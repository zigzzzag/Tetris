package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.matr.Matr;
import org.zgame.utils.Constants;

import java.awt.Graphics2D;
import java.util.Arrays;

/**
 * Created by SBT-Nikiforov-MO on 28.05.2015.
 */
public class RootGlass {

    private static final Logger log = LoggerFactory.getLogger(RootGlass.class);
    private int rowCount = Constants.MATR_ROW;
    private int columnCount = Constants.MATR_COLUMN;
    private Matr filledGlass;

    public RootGlass() {
        filledGlass = new Matr(rowCount,columnCount);
    }

    public RootGlass(byte[][] filledGlass) {
        this();
        this.rowCount = filledGlass.length;
        this.columnCount = filledGlass[0].length;
        this.filledGlass.setMatr(filledGlass);
    }

    public void paintRootGlass(Graphics2D g2d) {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (filledGlass.getMatr()[row][column] != 0) {
                    FigurePaint.gradientFigure(g2d,
                            GradientColors.getLightColorByNum(filledGlass.getMatr()[row][column]), GradientColors.getDarkColorByNum(filledGlass.getMatr()[row][column]),
                            Matr.converFromIndexColumn(column), Matr.converFromIndexRow(row));
                }
            }
        }
    }

    public void deleteFullLine(int lineNumber) {
        for (int row = lineNumber - 1; row >= 0; row--) {
            for (int column = 0; column < Constants.MATR_COLUMN; column++) {
                filledGlass.getMatr()[row + 1][column] = filledGlass.getMatr()[row][column];
                filledGlass.getMatr()[row][column] = 0;
            }
        }
    }

    public Boolean verifyGameOver() {
        boolean over = false;
        for (int j = 2; j < Constants.MATR_COLUMN - 2; j++) {
            if (filledGlass.getMatr()[0][j] != 0) {
                over = true;
            }
        }

        return false;
    }

    public boolean hasIntersectionWithMatr(byte[][] matr) {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (matr[row][column] != 0 && filledGlass.getMatr()[row][column] != 0) {
                    log.trace("RootGlass has intersection with matr: (row, column) -> ({},{})", row, column);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(filledGlass.getMatr());
    }

    public Matr getFilledGlass() {
        return filledGlass;
    }

    public void setFilledGlass(Matr filledGlass) {
        this.filledGlass = filledGlass;
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
