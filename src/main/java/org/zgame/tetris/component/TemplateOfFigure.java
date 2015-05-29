/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.utils.Constants;

import java.util.Arrays;
import java.util.Random;

/**
 * @author user
 */
public class TemplateOfFigure {

    private static final Logger log = LoggerFactory.getLogger(TemplateOfFigure.class);
    private byte[][] figure = new byte[Constants.matrY][Constants.matrX];
    private FigureType typeOfFigure;
    private byte colorByte;
    private int row;
    private int column;

    public TemplateOfFigure() {
    }

    public TemplateOfFigure(int figureType) {
        this(figureType, 0, 4);
    }

    public TemplateOfFigure(FigureType figureType, int row, int column) {
        this(figureType.getValue(), row, column);
    }

    public TemplateOfFigure(int figureTypeInt, int row, int column) {
        this.typeOfFigure = FigureType.getTypeByIntVal(figureTypeInt);
        this.row = row;
        this.column = column;

        clear(figure);
        switch (typeOfFigure) {
            //  **
            // **
            case S: {
                figure[0 + row][1 + column] = 1;
                figure[0 + row][2 + column] = 1;
                figure[1 + row][1 + column] = 1;
                figure[1 + row][0 + column] = 1;
                break;
            }
            // **
            //  **
            case S_R: {
                figure[0 + row][0 + column] = 1;
                figure[0 + row][1 + column] = 1;
                figure[1 + row][1 + column] = 1;
                figure[1 + row][2 + column] = 1;
                break;
            }
            // **
            // *
            // *
            case G: {
                figure[0 + row][0 + column] = 1;
                figure[0 + row][1 + column] = 1;
                figure[1 + row][0 + column] = 1;
                figure[2 + row][0 + column] = 1;
                break;
            }
            // **
            //  *
            //  *
            case G_R: {
                figure[0 + row][0 + column] = 1;
                figure[0 + row][1 + column] = 1;
                figure[1 + row][1 + column] = 1;
                figure[2 + row][1 + column] = 1;
                break;
            }
            // ***
            //  *
            case T: {
                figure[0 + row][0 + column] = 1;
                figure[0 + row][1 + column] = 1;
                figure[0 + row][2 + column] = 1;
                figure[1 + row][1 + column] = 1;
                break;
            }
            // **
            // **
            case QUADRATE: {
                figure[0 + row][0 + column] = 1;
                figure[0 + row][1 + column] = 1;
                figure[1 + row][0 + column] = 1;
                figure[1 + row][1 + column] = 1;
                break;
            }
            //  ****
            case STICK: {
                figure[0 + row][-1 + column] = 1;
                figure[0 + row][0 + column] = 1;
                figure[0 + row][1 + column] = 1;
                figure[0 + row][2 + column] = 1;
                break;
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

    public boolean isDownBarrier(RootGlass rootGlass) {
        for (int j = 0; j < Constants.matrX; j++) {
            if (figure[Constants.matrY - 1][j] != 0) {
                return true;
            }
        }
        for (int i = Constants.matrY - 1; i > 0; i--) {
            for (int j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0 && rootGlass.getFilledGlass()[i + 1][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void down() {
        for (int i = Constants.matrY - 1; i >= 0; i--) {//проходим снизу вверх чтобы опускать фигуру
            for (int j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    figure[i + 1][j] = figure[i][j];
                    figure[i][j] = 0;
                }
            }
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

    public void left(RootGlass rootGlass) {
        boolean leftAvailable = true;
        byte minX = getMinCoordinate()[0];

        outherloop:
        for (int i = 0; i < Constants.matrY; i++) {
            for (int j = 1; j < Constants.matrX; j++) {
                if (figure[i][j] != 0 && rootGlass.getFilledGlass()[i][j - 1] != 0) {
                    leftAvailable = false;
                    break outherloop;
                }
            }
        }

        if (minX > 0 && leftAvailable) {
            for (int i = 0; i < Constants.matrY; i++) {
                for (int j = 0; j < Constants.matrX; j++) {
                    if (figure[i][j] != 0) {
                        figure[i][j - 1] = figure[i][j];
                        figure[i][j] = 0;
                    }
                }
            }
        }
    }

    private boolean isRightAvailable(RootGlass rootGlass) {
        byte maxX = getMaxCoordinate()[0];
        if (maxX > rootGlass.getColumnCount()) {
            log.info("TOF: '{}' is not right available, because maxX > rootGlass.getColumnCount(); maxX = {}, rootGlass.getColumnCount() = {}",
                    typeOfFigure, maxX, rootGlass.getColumnCount());
            return false;
        }

        TemplateOfFigure tof_right = new TemplateOfFigure(typeOfFigure, row, column + 1);
        if (rootGlass.hasIntersectionWithFigure(tof_right)) {
            log.debug("TOF: '{}' is not right available, because rootGlass.hasIntersectionWithFigure(tof_right)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveRightForce(RootGlass rootGlass) {
        for (int row = 0; row < rootGlass.getRowCount(); row++) {
            for (int column = rootGlass.getColumnCount() - 1; column >= 0; column--) {
                if (figure[row][column] != 0) {
                    figure[row][column + 1] = figure[row][column];
                    figure[row][column] = 0;
                }
            }
        }
        column++;

        log.debug("TOF: '{}' move right force", typeOfFigure);
    }

    public void moveRight(RootGlass rootGlass) {
        if (isRightAvailable(rootGlass)) {
            moveRightForce(rootGlass);
        }
    }

    public void rotate(RootGlass rootGlass) {
        byte[] minCoord = getMinCoordinate();
        byte minX = minCoord[0];
        byte minY = minCoord[1];

        byte leftMoveBarrier = 0;
        if (typeOfFigure.equals(FigureType.STICK)) {
            if (minX > 6) {
                leftMoveBarrier = (byte) (minX - 6);
            }
        } else {
            if (minX > 7) {
                leftMoveBarrier = (byte) (minX - 7);
            }
        }
        for (int i = 0; i < leftMoveBarrier; i++) {
            left(rootGlass);
        }

        for (byte i = 0; i < Constants.matrY; i++) {
            for (byte j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    minX = minX >= j ? j : minX;
                    minY = minY >= i ? i : minY;
                }
            }
        }

        if (minY + typeOfFigure.getValue() / 7 > 17) {//нельзя поворачивать когда фигура находится слишком низко
            return;
        }

        byte sizeRotY = (byte) (typeOfFigure.equals(FigureType.STICK) ? 4 : 3);
        byte sizeRotX = (byte) (typeOfFigure.equals(FigureType.STICK) ? 4 : 3);

        byte leftMove = (byte) (typeOfFigure.equals(FigureType.STICK) ? 4 : 3);
        for (byte i = 0; i < sizeRotY; i++) {
            for (byte j = 0; j < sizeRotX; j++) {
                if (rootGlass.getFilledGlass()[i + minY][j + minX] != 0) {
                    if (j < leftMove) {
                        leftMove = j;
                    }
                }
            }
        }

        if (leftMove != sizeRotX) {
            for (int k = 0; k < sizeRotX - leftMove; k++) {
                left(rootGlass);
            }
            minX -= sizeRotX - leftMove;
            if (minX < 0) {
                return;
            }
        }

        byte firstColumnCount = 0; //вопрос с инициализацией
        byte[][] figureLocal = new byte[sizeRotY][sizeRotX];
        byte[][] figureRotateLocal = new byte[sizeRotY][sizeRotX];

        for (int i = 0; i < sizeRotY; i++) {
            for (int j = 0; j < sizeRotX; j++) {
                if (typeOfFigure.equals(FigureType.STICK)) {
                    figureLocal[i][j] = figure[i + minY][j + minX];
                    if (figureLocal[i][j] != 0) {
                        figureRotateLocal[j][i] = figureLocal[i][j];
                    }
                } else {
                    figureLocal[i][j] = figure[i + minY][j + minX];
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
                if (figureRotateLocal[i][j] != 0 && rootGlass.getFilledGlass()[i + minY][j + minX] != 0) {
                    flag = false;
                    break bbb;
                }
            }
        }

        if (flag) {
            for (int i = 0; i < sizeRotY; i++) {
                System.arraycopy(figureRotateLocal[i], 0, figure[i + minY], minX, sizeRotX);//it is necessary to examine
            }
        } else {
        }
    }

    public byte[] getMinCoordinate() {
        byte[] minCoord = {9, 19};
        for (byte i = 0; i < Constants.matrY; i++) {// minCoord[0] is x, minCoord[1] is y
            for (byte j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    minCoord[0] = minCoord[0] >= j ? j : minCoord[0];
                    minCoord[1] = minCoord[1] >= i ? i : minCoord[1];
                }
            }
        }
        return minCoord;
    }

    public byte[] getMaxCoordinate() {// maxCoord[0] is x, maxCoord[1] is y
        byte[] maxCoord = {0, 0};
        for (byte i = 0; i < Constants.matrY; i++) {
            for (byte j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    maxCoord[0] = maxCoord[0] <= j ? j : maxCoord[0];
                    maxCoord[1] = maxCoord[1] <= i ? i : maxCoord[1];
                }
            }
        }
        return maxCoord;
    }

    public byte getColorByte() {
        return colorByte;
    }

    public void setColorByte(byte colorByte) {
        this.colorByte = colorByte;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public FigureType getTypeOfFigure() {
        return typeOfFigure;
    }

    public void setTypeOfFigure(FigureType typeOfFigure) {
        this.typeOfFigure = typeOfFigure;
    }

    public static void main(String[] args) {
        TemplateOfFigure tof = new TemplateOfFigure(1, 3, 3);
        log.info(Arrays.deepToString(tof.getFigure()));
    }
}
