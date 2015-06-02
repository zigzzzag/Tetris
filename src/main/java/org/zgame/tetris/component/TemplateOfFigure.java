/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.comedowntime.ComeDownTime;
import org.zgame.tetris.component.comedowntime.TestComeDownTime;
import org.zgame.utils.Constants;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * @author user
 */
public class TemplateOfFigure {

    private static final Logger log = LoggerFactory.getLogger(TemplateOfFigure.class);
    private int rowCount = Constants.matrY;
    private int columnCount = Constants.matrX;
    private byte[][] figure = new byte[rowCount][columnCount];

    //каждую фигуру(кроме палки) можно впихнуть в квадрат 3х3(SubQuadrate)
    private int rowSubQuadrate;
    private int columnSubQuadrate;
    private FigureType typeOfFigure;
    private byte colorByte;
    private ComeDownTime comeDownTime;
    private FigureState state = FigureState.NORMAL;

    public TemplateOfFigure() {
        this.comeDownTime = new TestComeDownTime();
    }

    public TemplateOfFigure(int figureType) {
        this(figureType, 0, 4);
    }

    public TemplateOfFigure(FigureType figureType, int row, int columnSubQuadrate) {
        this(figureType.getValue(), row, columnSubQuadrate);
    }

    public TemplateOfFigure(int figureTypeInt, int row, int columnSubQuadrate) {
        this();
        this.typeOfFigure = FigureType.getTypeByIntVal(figureTypeInt);
        this.rowSubQuadrate = row;
        this.columnSubQuadrate = columnSubQuadrate;

        clear(figure);
        switch (typeOfFigure) {
            //  **
            // **
            case S: {
                figure[0 + row][1 + columnSubQuadrate] = 1;
                figure[0 + row][2 + columnSubQuadrate] = 1;
                figure[1 + row][1 + columnSubQuadrate] = 1;
                figure[1 + row][0 + columnSubQuadrate] = 1;
                break;
            }
            // **
            //  **
            case S_R: {
                figure[0 + row][0 + columnSubQuadrate] = 1;
                figure[0 + row][1 + columnSubQuadrate] = 1;
                figure[1 + row][1 + columnSubQuadrate] = 1;
                figure[1 + row][2 + columnSubQuadrate] = 1;
                break;
            }
            // **
            // *
            // *
            case G: {
                figure[0 + row][0 + columnSubQuadrate] = 1;
                figure[0 + row][1 + columnSubQuadrate] = 1;
                figure[1 + row][0 + columnSubQuadrate] = 1;
                figure[2 + row][0 + columnSubQuadrate] = 1;
                break;
            }
            // **
            //  *
            //  *
            case G_R: {
                figure[0 + row][0 + columnSubQuadrate] = 1;
                figure[0 + row][1 + columnSubQuadrate] = 1;
                figure[1 + row][1 + columnSubQuadrate] = 1;
                figure[2 + row][1 + columnSubQuadrate] = 1;
                break;
            }
            // ***
            //  *
            case T: {
                figure[0 + row][0 + columnSubQuadrate] = 1;
                figure[0 + row][1 + columnSubQuadrate] = 1;
                figure[0 + row][2 + columnSubQuadrate] = 1;
                figure[1 + row][1 + columnSubQuadrate] = 1;
                break;
            }
            // **
            // **
            case QUADRATE: {
                figure[0 + row][0 + columnSubQuadrate] = 1;
                figure[0 + row][1 + columnSubQuadrate] = 1;
                figure[1 + row][0 + columnSubQuadrate] = 1;
                figure[1 + row][1 + columnSubQuadrate] = 1;
                break;
            }
            //  ****
            case STICK: {
                figure[0 + row][-1 + columnSubQuadrate] = 1;
                figure[0 + row][0 + columnSubQuadrate] = 1;
                figure[0 + row][1 + columnSubQuadrate] = 1;
                figure[0 + row][2 + columnSubQuadrate] = 1;
                break;
            }
        }
    }

    public void paintFigure(Graphics2D g2d) {
        for (int row = 0; row < Constants.matrY; row++) {
            for (int column = 0; column < Constants.matrX; column++) {
                if (this.getFigure()[row][column] != 0) {
                    FigurePaint.gradientFigure(g2d, FigurePaint.lightColor(this.getFigure()[row][column]),
                            FigurePaint.darkColor(this.getFigure()[row][column]), FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row));
                }
            }
        }
    }

    public void paintFigureShadow(Graphics2D g2d) {
        for (int row = 0; row < Constants.matrY; row++) {
            for (int column = 0; column < Constants.matrX; column++) {
                if (this.getFigure()[row][column] != 0) {
                    g2d.setColor(Constants.alphaShadow);
                    g2d.fillRect(FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row), Constants.quadrateSize, Constants.quadrateSize);
                    g2d.setColor(Color.black);
                    g2d.drawRect(FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row), Constants.quadrateSize, Constants.quadrateSize);
                }
            }
        }
    }

    public void paintFigureNext(Graphics2D g2d) {
        for (int row = 0; row < Constants.matrY; row++) {
            for (int column = 0; column < Constants.matrX; column++) {
                if (this.getFigure()[row][column] != 0) {
                    FigurePaint.gradientFigure(g2d, FigurePaint.lightColor(this.getFigure()[row][column]),
                            FigurePaint.darkColor(this.getFigure()[row][column]), FigurePaint.converFromIndexColumn(column) - 300, FigurePaint.converFromIndexRow(row));
                }
            }
        }
    }

    public void paintRandomColor() {
        this.colorByte = (byte) (new Random().nextInt(8) + 1);
        for (int i = 0; i < Constants.matrY; i++) {
            for (int j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    figure[i][j] = (byte) colorByte;
                }
            }
        }
    }

    public byte[][] getFigure() {
        return figure;
    }

    public void setFigure(byte[][] figure) {
        this.figure = figure;
    }

    public void clear(byte[][] figure) {
        for (int i = 0; i < Constants.matrY; i++) {
            for (int j = 0; j < Constants.matrX; j++) {
                figure[i][j] = 0;
            }
        }
    }

    public boolean isDownAvailable(RootGlass rootGlass) {
        byte maxRow = getMaxRow();
        if (maxRow >= rootGlass.getRowCount() - 1) {
            log.debug("TOF: '{}' is not DOWN available, because maxRow >= rootGlass.getRowCount() - 1; maxRow = {}, rootGlass.getColumnCount() = {}",
                    typeOfFigure, maxRow, rootGlass.getColumnCount());
            return false;
        }

        TemplateOfFigure tof_down = new TemplateOfFigure(typeOfFigure, rowSubQuadrate + 1, columnSubQuadrate);

        if (rootGlass.hasIntersectionWithFigure(tof_down)) {
            log.debug("TOF: '{}' is not DOWN available, because rootGlass.hasIntersectionWithFigure(tof_down)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveDownForce() {
        for (int row = rowCount - 1; row >= 0; row--) {
            for (int column = 0; column < columnCount; column++) {
                if (figure[row][column] != 0) {
                    figure[row + 1][column] = figure[row][column];
                    figure[row][column] = 0;
                }
            }
        }
        rowSubQuadrate++;
        log.debug("TOF: '{}' move DOWN force", typeOfFigure);
    }

    public void moveDown(RootGlass rootGlass) {
        if (isDownAvailable(rootGlass)) {
            moveDownForce();
        }
    }

    public void up() {
        for (int i = 0; i < Constants.matrY; i++) {
            for (int j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    figure[i - 1][j] = figure[i][j];
                    figure[i][j] = 0;
                }
            }
        }
    }

    private boolean isLeftAvailable(RootGlass rootGlass) {
        byte minColumn = getMinColumn();
        if (minColumn <= 0) {
            log.debug("TOF: '{}' is not LEFT available, because minColumn <= 0; minColumn = {}, rootGlass.getColumnCount() = {}",
                    typeOfFigure, minColumn, rootGlass.getColumnCount());
            return false;
        }

        TemplateOfFigure tof_left = new TemplateOfFigure(typeOfFigure, rowSubQuadrate, columnSubQuadrate - 1);
        if (rootGlass.hasIntersectionWithFigure(tof_left)) {
            log.debug("TOF: '{}' is not LEFT available, because rootGlass.hasIntersectionWithFigure(tof_left)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveLeftForce() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (figure[row][column] != 0) {
                    figure[row][column - 1] = figure[row][column];
                    figure[row][column] = 0;
                }
            }
        }
        columnSubQuadrate--;
        log.debug("TOF: '{}' move LEFT force", typeOfFigure);
    }

    public void moveLeft(RootGlass rootGlass) {
        if (isLeftAvailable(rootGlass)) {
            moveLeftForce();
        }
    }

    private boolean isRightAvailable(RootGlass rootGlass) {
        byte maxColumn = getMaxColumn();
        if (maxColumn >= rootGlass.getColumnCount() - 1) {
            log.debug("TOF: '{}' is not RIGHT available, because maxColumn > rootGlass.getColumnCount() - 1; maxColumn = {}, rootGlass.getColumnCount() = {}",
                    typeOfFigure, maxColumn, rootGlass.getColumnCount());
            return false;
        }

        TemplateOfFigure tof_right = new TemplateOfFigure(typeOfFigure, rowSubQuadrate, columnSubQuadrate + 1);
        if (rootGlass.hasIntersectionWithFigure(tof_right)) {
            log.debug("TOF: '{}' is not RIGHT available, because rootGlass.hasIntersectionWithFigure(tof_right)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveRightForce() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = columnCount - 1; column >= 0; column--) {
                if (figure[row][column] != 0) {
                    figure[row][column + 1] = figure[row][column];
                    figure[row][column] = 0;
                }
            }
        }
        columnSubQuadrate++;
        log.debug("TOF: '{}' move right force", typeOfFigure);
    }

    public void moveRight(RootGlass rootGlass) {
        if (isRightAvailable(rootGlass)) {
            moveRightForce();
        }
    }

    public void rotate(RootGlass rootGlass) {
        byte minColumn = getMinColumn();
        byte minRow = getMinRow();

        byte leftMoveBarrier = 0;
        if (typeOfFigure.equals(FigureType.STICK)) {
            if (minColumn > 6) {
                leftMoveBarrier = (byte) (minColumn - 6);
            }
        } else {
            if (minColumn > 7) {
                leftMoveBarrier = (byte) (minColumn - 7);
            }
        }
        for (int i = 0; i < leftMoveBarrier; i++) {
            moveLeft(rootGlass);
        }

        for (byte i = 0; i < Constants.matrY; i++) {
            for (byte j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    minColumn = minColumn >= j ? j : minColumn;
                    minRow = minRow >= i ? i : minRow;
                }
            }
        }

        if (minRow + typeOfFigure.getValue() / 7 > 17) {//нельзя поворачивать когда фигура находится слишком низко
            return;
        }

        byte sizeRotY = (byte) (typeOfFigure.equals(FigureType.STICK) ? 4 : 3);
        byte sizeRotX = (byte) (typeOfFigure.equals(FigureType.STICK) ? 4 : 3);

        byte leftMove = (byte) (typeOfFigure.equals(FigureType.STICK) ? 4 : 3);
        for (byte i = 0; i < sizeRotY; i++) {
            for (byte j = 0; j < sizeRotX; j++) {
                if (rootGlass.getFilledGlass()[i + minRow][j + minColumn] != 0) {
                    if (j < leftMove) {
                        leftMove = j;
                    }
                }
            }
        }

        if (leftMove != sizeRotX) {
            for (int k = 0; k < sizeRotX - leftMove; k++) {
                moveLeft(rootGlass);
            }
            minColumn -= sizeRotX - leftMove;
            if (minColumn < 0) {
                return;
            }
        }

        byte firstColumnCount = 0; //вопрос с инициализацией
        byte[][] figureLocal = new byte[sizeRotY][sizeRotX];
        byte[][] figureRotateLocal = new byte[sizeRotY][sizeRotX];

        for (int i = 0; i < sizeRotY; i++) {
            for (int j = 0; j < sizeRotX; j++) {
                if (typeOfFigure.equals(FigureType.STICK)) {
                    figureLocal[i][j] = figure[i + minRow][j + minColumn];
                    if (figureLocal[i][j] != 0) {
                        figureRotateLocal[j][i] = figureLocal[i][j];
                    }
                } else {
                    figureLocal[i][j] = figure[i + minRow][j + minColumn];
                    if (figureLocal[i][j] != 0) {
                        figureRotateLocal[j][2 - i] = figureLocal[i][j];
                    }
                }
            }
        }

        if (!typeOfFigure.equals(FigureType.STICK)) {
            for (int i = 0; i < sizeRotY; i++) {
                if (figureRotateLocal[i][0] != 0) {
                    firstColumnCount++;
                }
            }

            if (firstColumnCount == 0) { // смещение вправо матрицы 3 на 3
                for (int i = 0; i < sizeRotY; i++) {
                    for (int j = 0; j < sizeRotX; j++) {
                        if (typeOfFigure.equals(FigureType.STICK)) {
                            if (figureRotateLocal[i][j] != 0) {
                                figureRotateLocal[i][j - 2] = figureRotateLocal[i][j];
                                figureRotateLocal[i][j] = 0;
                            }
                        } else {
                            if (figureRotateLocal[i][j] != 0) {
                                figureRotateLocal[i][j - 1] = figureRotateLocal[i][j];
                                figureRotateLocal[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        boolean flag = true;
        bbb:
        for (int i = 0; i < sizeRotY; i++) {
            for (int j = 0; j < sizeRotX; j++) {
                if (figureRotateLocal[i][j] != 0 && rootGlass.getFilledGlass()[i + minRow][j + minColumn] != 0) {
                    flag = false;
                    break bbb;
                }
            }
        }

        if (flag) {
            for (int i = 0; i < sizeRotY; i++) {
                System.arraycopy(figureRotateLocal[i], 0, figure[i + minRow], minColumn, sizeRotX);//it is necessary to examine
            }
        } else {
        }
    }

    public long getComeDownTime(int totalPoints) {
        if (FigureState.FALL.equals(state)) {
            return 20;
        }
        return comeDownTime.getComeDownTime(totalPoints);
    }

    private boolean isEmptyRow(byte rowNum) {
        for (int column = 0; column < columnCount; column++) {
            if (figure[rowNum][column] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyColumn(byte columnNum) {
        for (int row = 0; row < rowCount; row++) {
            if (figure[row][columnNum] != 0) {
                return false;
            }
        }
        return true;
    }

    private byte getMinRow() {
        for (byte row = 0; row < rowCount; row++) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MAX rowSubQuadrate is -1 !!!");
        return -1;
    }

    private byte getMinColumn() {
        for (byte column = 0; column < columnCount; column++) {
            if (!isEmptyColumn(column)) {
                return column;
            }
        }
        log.error("MIN columnSubQuadrate is -1 !!!");
        return -1;
    }

    private byte getMaxRow() {
        for (byte row = (byte) (rowCount - 1); row >= 0; row--) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MIN rowSubQuadrate is -1 !!!");
        return -1;
    }

    private byte getMaxColumn() {
        for (byte column = (byte) (columnCount - 1); column >= 0; column--) {
            if (!isEmptyColumn(column)) {
                return column;
            }
        }
        log.error("MAX columnSubQuadrate is -1 !!!");
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(figure);
    }

    public byte getColorByte() {
        return colorByte;
    }

    public void setColorByte(byte colorByte) {
        this.colorByte = colorByte;
    }

    public int getRowSubQuadrate() {
        return rowSubQuadrate;
    }

    public void setRowSubQuadrate(int rowSubQuadrate) {
        this.rowSubQuadrate = rowSubQuadrate;
    }

    public int getColumnSubQuadrate() {
        return columnSubQuadrate;
    }

    public void setColumnSubQuadrate(int columnSubQuadrate) {
        this.columnSubQuadrate = columnSubQuadrate;
    }

    public FigureType getTypeOfFigure() {
        return typeOfFigure;
    }

    public void setTypeOfFigure(FigureType typeOfFigure) {
        this.typeOfFigure = typeOfFigure;
    }

    public FigureState getState() {
        return state;
    }

    public void setState(FigureState state) {
        this.state = state;
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

    public static void main(String[] args) {
        TemplateOfFigure tof = new TemplateOfFigure(1, 3, 3);
        log.info(Arrays.deepToString(tof.getFigure()));
    }
}
