/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.mycompany.stage.TetrisStage;

import java.util.Random;

/**
 * @author user
 */
public class TemplateOfFigure {

    private byte[][] figure = new byte[Constants.matrY][Constants.matrX];
    private int typeOfFigure;

    public TemplateOfFigure() {
    }

    public TemplateOfFigure(int typeFigure) {
        typeOfFigure = typeFigure;
        clear(figure);
        switch (typeFigure) {
            //  **
            // **
            case 1: {
                figure[0][5] = 1;
                figure[0][6] = 1;
                figure[1][5] = 1;
                figure[1][4] = 1;
                break;
            }
            // **
            //  **
            case 2: {
                figure[0][4] = 1;
                figure[0][5] = 1;
                figure[1][5] = 1;
                figure[1][6] = 1;
                break;
            }
            // **
            // *
            // *
            case 3: {
                figure[0][4] = 1;
                figure[0][5] = 1;
                figure[1][4] = 1;
                figure[2][4] = 1;
                break;
            }
            // **
            //  *
            //  *
            case 4: {
                figure[0][4] = 1;
                figure[0][5] = 1;
                figure[1][5] = 1;
                figure[2][5] = 1;
                break;
            }
            // ***
            //  *
            case 5: {
                figure[0][4] = 1;
                figure[0][5] = 1;
                figure[0][6] = 1;
                figure[1][5] = 1;
                break;
            }
            // **
            // **
            case 6: {
                figure[0][4] = 1;
                figure[0][5] = 1;
                figure[1][4] = 1;
                figure[1][5] = 1;
                break;
            }
            //  *
            //  *
            //  *
            //  *
            case 7: {
                figure[0][3] = 1;
                figure[0][4] = 1;
                figure[0][5] = 1;
                figure[0][6] = 1;
                break;
            }
        }

        byte indexColor = (byte) (new Random().nextInt(8) + 1);
        for (int i = 0; i < Constants.matrY; i++) {
            for (int j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    figure[i][j] = indexColor;
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

    public boolean isDownBarrier() {
        for (int j = 0; j < Constants.matrX; j++) {
            if (figure[Constants.matrY - 1][j] != 0) {
                return true;
            }
        }
        for (int i = Constants.matrY - 1; i > 0; i--) {
            for (int j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0 && TetrisStage.getFigureSave()[i + 1][j] != 0) {
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

    public void left() {
        boolean leftAvailable = true;
        byte minX = getMinCoordinate()[0];

        outherloop:
        for (int i = 0; i < Constants.matrY; i++) {
            for (int j = 1; j < Constants.matrX; j++) {
                if (figure[i][j] != 0 && TetrisStage.getFigureSave()[i][j - 1] != 0) {
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

    public void right() {
        boolean rightAvailable = true;
        byte maxX = getMaxCoordinate()[0];

        outherloop:
        for (int i = 0; i < Constants.matrY; i++) {
            for (int j = 0; j < Constants.matrX - 1; j++) {
                if (figure[i][j] != 0 && TetrisStage.getFigureSave()[i][j + 1] != 0) {
                    rightAvailable = false;
                    break outherloop;
                }
            }
        }

        if (maxX < Constants.matrX - 1 && rightAvailable) {
            for (int i = 0; i < Constants.matrY; i++) {
                for (int j = Constants.matrX - 1; j >= 0; j--) {
                    if (figure[i][j] != 0) {
                        figure[i][j + 1] = figure[i][j];
                        figure[i][j] = 0;
                    }
                }
            }
        }
    }

    public void rotate() {
        byte[] minCoord = getMinCoordinate();
        byte minX = minCoord[0];
        byte minY = minCoord[1];

        byte leftMoveBarrier = 0;
        if (typeOfFigure == 7) {
            if (minX > 6) {
                leftMoveBarrier = (byte) (minX - 6);
            }
        } else {
            if (minX > 7) {
                leftMoveBarrier = (byte) (minX - 7);
            }
        }
        for (int i = 0; i < leftMoveBarrier; i++) {
            left();
        }

        for (byte i = 0; i < Constants.matrY; i++) {
            for (byte j = 0; j < Constants.matrX; j++) {
                if (figure[i][j] != 0) {
                    minX = minX >= j ? j : minX;
                    minY = minY >= i ? i : minY;
                }
            }
        }

        if (minY + typeOfFigure / 7 > 17) {//нельзя поворачивать когда фигура находится слишком низко
            return;
        }

        byte sizeRotY = (byte) (typeOfFigure == 7 ? 4 : 3);
        byte sizeRotX = (byte) (typeOfFigure == 7 ? 4 : 3);

        byte leftMove = (byte) (typeOfFigure == 7 ? 4 : 3);
        for (byte i = 0; i < sizeRotY; i++) {
            for (byte j = 0; j < sizeRotX; j++) {
                if (TetrisStage.getFigureSave()[i + minY][j + minX] != 0) {
                    if (j < leftMove) {
                        leftMove = j;
                    }
                }
            }
        }

        if (leftMove != sizeRotX) {
            for (int k = 0; k < sizeRotX - leftMove; k++) {
                left();
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
                if (typeOfFigure == 7) {
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

        if (typeOfFigure != 7) {
            for (int i = 0; i < sizeRotY; i++) {
                if (figureRotateLocal[i][0] != 0) {
                    firstColumnCount++;
                }
            }

            if (firstColumnCount == 0) { // смещение вправо матрицы 3 на 3
                for (int i = 0; i < sizeRotY; i++) {
                    for (int j = 0; j < sizeRotX; j++) {
                        if (typeOfFigure == 7) {
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
                if (figureRotateLocal[i][j] != 0 && TetrisStage.getFigureSave()[i + minY][j + minX] != 0) {
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
}
