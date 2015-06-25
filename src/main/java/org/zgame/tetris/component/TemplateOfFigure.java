/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.comedowntime.ComeDownTime;
import org.zgame.tetris.component.comedowntime.TestComeDownTime;
import org.zgame.tetris.component.matr.Matr;
import org.zgame.tetris.component.matr.SubMatr;
import org.zgame.utils.Constants;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * @author user
 */
public class TemplateOfFigure {

    private static final Logger log = LoggerFactory.getLogger(TemplateOfFigure.class);
    private Matr figure;
    private SubMatr subFigure;

    //каждую фигуру(кроме палки) можно впихнуть в квадрат 3х3(SubQuadrate)
//    private int rowSubQuadrate;
//    private int columnSubQuadrate;
    private FigureType typeOfFigure;
    private byte colorByte;
    private ComeDownTime comeDownTime;
    private FigureState state = FigureState.NORMAL;
    private RotationAngle rotationAngle = new RotationAngle(0);

    public TemplateOfFigure rotationAngleInt(int angle) {
        this.rotationAngle.setAngle(angle);
        for (int rotateCount = 0; rotateCount < rotationAngle.getCountRotate(); rotateCount++) {
            rotateForce();
        }
        return this;
    }

    public TemplateOfFigure() {
        this.figure = new Matr(Constants.matrY, Constants.matrX);
        this.comeDownTime = new TestComeDownTime();
    }

    public TemplateOfFigure(int figureTypeInt) {
        this(figureTypeInt, (byte) 0, (byte) 4);
    }

    public TemplateOfFigure(FigureType figureType, int rowSubQuadrate, int columnSubQuadrate) {
        this(figureType.getValue(), rowSubQuadrate, columnSubQuadrate);
    }

    public TemplateOfFigure(int figureTypeInt, int rowSubQuadrate, int columnSubQuadrate) {
        this();
        this.typeOfFigure = FigureType.getTypeByIntVal(figureTypeInt);

        clear();
        switch (typeOfFigure) {
            //  **
            // **
            case S: {
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 2 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 0 + columnSubQuadrate);
                subFigure = new SubMatr(3, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            // **
            //  **
            case S_R: {
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 2 + columnSubQuadrate);
                subFigure = new SubMatr(3, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            // **
            // *
            // *
            case G: {
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 2 + rowSubQuadrate, 0 + columnSubQuadrate);
                subFigure = new SubMatr(3, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            // **
            //  *
            //  *
            case G_R: {
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 2 + rowSubQuadrate, 1 + columnSubQuadrate);
                subFigure = new SubMatr(3, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            // ***
            //  *
            case T: {
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 2 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                subFigure = new SubMatr(3, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            // **
            // **
            case QUADRATE: {
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                subFigure = new SubMatr(2, 2).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            //  ****
            case STICK: {
                figure.setElement(1, 0 + rowSubQuadrate, -1 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 2 + columnSubQuadrate);
                subFigure = new SubMatr(4, 4).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
        }
    }

    public void paintFigure(Graphics2D g2d) {
        for (int row = 0; row < Constants.matrY; row++) {
            for (int column = 0; column < Constants.matrX; column++) {
                if (figure.getElement(row, column) != 0) {
                    FigurePaint.gradientFigure(g2d, FigurePaint.lightColor(figure.getElement(row, column)),
                            FigurePaint.darkColor(figure.getElement(row, column)), FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row));
                }
            }
        }
    }

    public void paintFigureShadow(Graphics2D g2d) {
        for (int row = 0; row < Constants.matrY; row++) {
            for (int column = 0; column < Constants.matrX; column++) {
                if (figure.getElement(row, column) != 0) {
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
                if (figure.getElement(row, column) != 0) {
                    FigurePaint.gradientFigure(g2d, FigurePaint.lightColor(figure.getElement(row, column)),
                            FigurePaint.darkColor(figure.getElement(row, column)), FigurePaint.converFromIndexColumn(column) - 300, FigurePaint.converFromIndexRow(row));
                }
            }
        }
    }

    public void paintRandomColor() {
        this.colorByte = (byte) (new Random().nextInt(8) + 1);
        this.figure.setAllNotNullElements(colorByte);
    }

    public Matr getFigure() {
        return figure;
    }

    public void setFigure(Matr figure) {
        this.figure = figure;
    }

    private void clear() {
        this.figure.clear();
    }

    public boolean isDownAvailable(RootGlass rootGlass) {
        byte maxRow = getMaxRow();
        if (maxRow >= rootGlass.getRowCount() - 1) {
            log.debug("TOF: '{}' is not DOWN available, because maxRow >= rootGlass.getRowCount() - 1; maxRow = {}, rootGlass.getColumnCount() = {}",
                    typeOfFigure, maxRow, rootGlass.getColumnCount());
            return false;
        }

        TemplateOfFigure tof_down = new TemplateOfFigure(typeOfFigure, subFigure.getRowCoord() + 1, subFigure.getColumnCoord());

        if (rootGlass.hasIntersectionWithFigure(tof_down)) {
            log.debug("TOF: '{}' is not DOWN available, because rootGlass.hasIntersectionWithFigure(tof_down)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveDownForce() {
        for (int row = figure.getRowCount() - 1; row >= 0; row--) {
            for (int column = 0; column < figure.getColumnCount(); column++) {
                if (figure.getMatr()[row][column] != 0) {
                    figure.getMatr()[row + 1][column] = figure.getMatr()[row][column];
                    figure.getMatr()[row][column] = 0;
                }
            }
        }
        subFigure.incrementRow();
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
                if (figure.getMatr()[i][j] != 0) {
                    figure.getMatr()[i - 1][j] = figure.getMatr()[i][j];
                    figure.getMatr()[i][j] = 0;
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

        TemplateOfFigure tofLeft = this.clone();
        tofLeft.moveLeftForce();
        if (rootGlass.hasIntersectionWithFigure(tofLeft)) {
            log.debug("TOF: '{}' is not LEFT available, because rootGlass.hasIntersectionWithFigure(tofLeft)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveLeftForce() {
        try {
            for (int row = 0; row < figure.getRowCount(); row++) {
                for (int column = 0; column < figure.getColumnCount(); column++) {
                    if (figure.getMatr()[row][column] != 0) {
                        figure.getMatr()[row][column - 1] = figure.getMatr()[row][column];
                        figure.getMatr()[row][column] = 0;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            log.error(ex.getMessage(), ex);
            return;
        }
        subFigure.decrementColumn();
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

        TemplateOfFigure tof_right = this.clone();
        tof_right.moveRightForce();
        if (rootGlass.hasIntersectionWithFigure(tof_right)) {
            log.debug("TOF: '{}' is not RIGHT available, because rootGlass.hasIntersectionWithFigure(tof_right)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveRightForce() {
        try {
            for (int row = 0; row < figure.getRowCount(); row++) {
                for (int column = figure.getColumnCount() - 2; column >= 0; column--) {
                    if (figure.getMatr()[row][column] != 0) {
                        figure.getMatr()[row][column + 1] = figure.getMatr()[row][column];
                        figure.getMatr()[row][column] = 0;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            log.error(ex.getMessage(), ex);
            return;
        }
        subFigure.incrementColumn();
        log.debug("move right force TOF: '{}'", this);
    }

    public void moveRight(RootGlass rootGlass) {
        if (isRightAvailable(rootGlass)) {
            moveRightForce();
        }
    }

    private boolean isRotateAvailable(RootGlass rootGlass) {
//        byte maxColumn = getMaxColumn();
//        if (maxColumn >= rootGlass.getColumnCount() - 1) {
//            log.debug("TOF: '{}' is not ROTATE available, because maxColumn > rootGlass.getColumnCount() - 1; maxColumn = {}, rootGlass.getColumnCount() = {}",
//                    typeOfFigure, maxColumn, rootGlass.getColumnCount());
//            return false;
//        }

        TemplateOfFigure tof_rotate = new TemplateOfFigure(typeOfFigure, subFigure.getRowCoord(), subFigure.getColumnCoord()).rotationAngleInt(90);
        if (rootGlass.hasIntersectionWithFigure(tof_rotate)) {
            log.debug("TOF: '{}' is not ROTATE available, because rootGlass.hasIntersectionWithFigure(tof_rotate)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    public void rotate(RootGlass rootGlass) {
        if (isRotateAvailable(rootGlass)) {
            rotateForce();
            rotationAngle.rotate();
        }
    }

    private void rotateForce() {
        subFigure.update(figure);
        subFigure.transposeMatrClockWise();
        figure.copySubMatr(subFigure);
    }

    public long getComeDownTime(int totalPoints) {
        if (FigureState.FALL.equals(state)) {
            return 20;
        }
        return comeDownTime.getComeDownTime(totalPoints);
    }

    private boolean isEmptyRow(byte rowNum) {
        for (int column = 0; column < figure.getColumnCount(); column++) {
            if (figure.getMatr()[rowNum][column] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyColumn(byte columnNum) {
        for (int row = 0; row < figure.getRowCount(); row++) {
            if (figure.getMatr()[row][columnNum] != 0) {
                return false;
            }
        }
        return true;
    }

    private byte getMinRow() {
        for (byte row = 0; row < figure.getRowCount(); row++) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MAX rowSubQuadrate is -1 !!!");
        return -1;
    }

    private byte getMinColumn() {
        for (byte column = 0; column < figure.getColumnCount(); column++) {
            if (!isEmptyColumn(column)) {
                return column;
            }
        }
        log.error("MIN columnSubQuadrate is -1 !!!");
        return -1;
    }

    private byte getMaxRow() {
        for (byte row = (byte) (figure.getRowCount() - 1); row >= 0; row--) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MIN rowSubQuadrate is -1 !!!");
        return -1;
    }

    private byte getMaxColumn() {
        for (byte column = (byte) (figure.getColumnCount() - 1); column >= 0; column--) {
            if (!isEmptyColumn(column)) {
                return column;
            }
        }
        log.error("MAX columnSubQuadrate is -1 !!!");
        return -1;
    }

    public TemplateOfFigure clone() {
        TemplateOfFigure tofClone = new TemplateOfFigure(typeOfFigure, subFigure.getRowCoord(), subFigure.getColumnCoord());
        return tofClone;
    }

    @Override
    public String toString() {
        return figure.hashCode() + " " + getTypeOfFigure().toString() + " " + hashCode() + ": " + Arrays.deepToString(figure.getMatr());
    }

    public byte getColorByte() {
        return colorByte;
    }

    public void setColorByte(byte colorByte) {
        this.colorByte = colorByte;
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
}
