/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.comedowntime.ComeDownTime;
import org.zgame.tetris.component.comedowntime.ComeDownTimeImpl;
import org.zgame.tetris.component.comedowntime.TestComeDownTime;
import org.zgame.tetris.component.matr.Matr;
import org.zgame.tetris.component.matr.MatrUtils;
import org.zgame.tetris.component.matr.SubMatr;
import org.zgame.utils.Constants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author user
 */
public class TemplateOfFigure {

    private static final Logger log = LoggerFactory.getLogger(TemplateOfFigure.class);
    private Matr figure;
    private SubMatr subFigure;
    private FigureType typeOfFigure;
    private byte colorByte;
    private ComeDownTime comeDownTime;
    private FigureState state = FigureState.NORMAL;
    private RotationAngle rotationAngle = new RotationAngle(0);
    private Lock lock = new ReentrantLock(false);

    public TemplateOfFigure(int rowCount, int columnCount) {
        this.figure = new Matr(rowCount, columnCount);
        this.comeDownTime = new ComeDownTimeImpl();
    }

    public TemplateOfFigure(FigureType figureType) {
        this(figureType, Constants.MATR_ROW, Constants.MATR_COLUMN, 0, 4);
    }

    public TemplateOfFigure(FigureType figureType, int rowSubQuadrate, int columnSubQuadrate) {
        this(figureType, Constants.MATR_ROW, Constants.MATR_COLUMN, rowSubQuadrate, columnSubQuadrate);
    }

    public TemplateOfFigure(FigureType figureType, int rowCount, int columnCount, int rowSubQuadrate, int columnSubQuadrate) {
        this(rowCount, columnCount);
        this.typeOfFigure = figureType;

        clear();
        switch (typeOfFigure) {
            //  **
            // **
            case S: {
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 2 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 0 + columnSubQuadrate);
                subFigure = new SubMatr(2, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            // **
            //  **
            case S_R: {
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 2 + columnSubQuadrate);
                subFigure = new SubMatr(2, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
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
                subFigure = new SubMatr(3, 2).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
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
                subFigure = new SubMatr(3, 2).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
            // ***
            //  *
            case T: {
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 2 + columnSubQuadrate);
                figure.setElement(1, 1 + rowSubQuadrate, 1 + columnSubQuadrate);
                subFigure = new SubMatr(2, 3).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
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
                figure.setElement(1, 0 + rowSubQuadrate, 0 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 1 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 2 + columnSubQuadrate);
                figure.setElement(1, 0 + rowSubQuadrate, 3 + columnSubQuadrate);
                subFigure = new SubMatr(1, 4).rowCoord(rowSubQuadrate).columnCoord(columnSubQuadrate);
                break;
            }
        }
    }

    public TemplateOfFigure rotationAngleInt(int angle) {
        this.rotationAngle.setAngle(angle);
        for (int rotateCount = 0; rotateCount < rotationAngle.getCountRotate(); rotateCount++) {
            rotateForce();
        }
        return this;
    }

    public void paintFigure(Graphics2D g2d) {
        for (int row = 0; row < Constants.MATR_ROW; row++) {
            for (int column = 0; column < Constants.MATR_COLUMN; column++) {
                if (figure.getElement(row, column) != 0) {
                    FigurePaint.gradientFigure(g2d, FigurePaint.lightColor(figure.getElement(row, column)),
                            FigurePaint.darkColor(figure.getElement(row, column)), FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row));
                }
            }
        }
    }

    public void paintFigureShadow(Graphics2D g2d) {
        for (int row = 0; row < Constants.MATR_ROW; row++) {
            for (int column = 0; column < Constants.MATR_COLUMN; column++) {
                if (figure.getElement(row, column) != 0) {
                    g2d.setColor(Constants.alphaShadow);
                    g2d.fillRect(FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row), Constants.QUADRATE_SIZE, Constants.QUADRATE_SIZE);
                    g2d.setColor(Color.black);
                    g2d.drawRect(FigurePaint.converFromIndexColumn(column), FigurePaint.converFromIndexRow(row), Constants.QUADRATE_SIZE, Constants.QUADRATE_SIZE);
                }
            }
        }
    }

    public void paintFigureNext(Graphics2D g2d) {
        for (int row = 0; row < Constants.MATR_ROW; row++) {
            for (int column = 0; column < Constants.MATR_COLUMN; column++) {
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

    private void clear() {
        this.figure.clear();
    }

    public boolean isDownAvailable(RootGlass rootGlass) {
        int maxRow = getMaxRow();
        if (maxRow < rootGlass.getRowCount() - 1) {
            byte[][] matrDown = MatrUtils.getDownMatr(figure.getMatr());
            if (!rootGlass.hasIntersectionWithMatr(matrDown)) {
                return true;
            }
        }
        log.debug("TOF: '{}' is not DOWN available", typeOfFigure);
        GameContext.INSTANCE.nextStep();
        return false;
    }

    public boolean isUpAvailable(RootGlass rootGlass) {
        int minRow = getMinRow();
        if (minRow > 0) {
            byte[][] matrUp = MatrUtils.getUpMatr(figure.getMatr());
            if (!rootGlass.hasIntersectionWithMatr(matrUp)) {
                return true;
            }
        }
        log.debug("TOF: '{}' is not UP available", typeOfFigure);
        return false;
    }

    private boolean isLeftAvailable(RootGlass rootGlass) {
        int minColumn = getMinColumn();
        if (minColumn > 0) {
            byte[][] matrLeft = MatrUtils.getLeftMatr(figure.getMatr());
            if (!rootGlass.hasIntersectionWithMatr(matrLeft)) {
                return true;
            }
        }
        log.debug("TOF: '{}' is not LEFT available", typeOfFigure);
        return false;
    }

    private boolean isRightAvailable(RootGlass rootGlass) {
        int maxColumn = getMaxColumn();
        if (maxColumn < rootGlass.getColumnCount() - 1) {
            byte[][] matrRight = MatrUtils.getRightMatr(figure.getMatr());
            if (!rootGlass.hasIntersectionWithMatr(matrRight)) {
                return true;
            }
        }
        log.debug("TOF: '{}' is not RIGHT available", typeOfFigure);
        return false;
    }

    private boolean isRotateAvailable(RootGlass rootGlass) {
        TemplateOfFigure tofRotate = this.clone();
        tofRotate.leftBeforeRotate();
        tofRotate.rotationAngleInt(90);
        if (rootGlass.hasIntersectionWithMatr(tofRotate.getFigure().getMatr())) {
            log.debug("TOF: '{}' is not ROTATE available, because rootGlass.hasIntersectionWithFigure(tofRotate)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveDownForce() {
        byte[][] matr = figure.getMatr();
        for (int row = figure.getRowCount() - 1; row >= 0; row--) {
            for (int column = 0; column < figure.getColumnCount(); column++) {
                if (matr[row][column] != 0) {
                    matr[row + 1][column] = matr[row][column];
                    matr[row][column] = 0;
                }
            }
        }
        subFigure.incrementRow();
        log.debug("TOF: '{}' move DOWN force", typeOfFigure);
    }

    private void moveUpForce() {
        byte[][] matr = figure.getMatr();
        for (int row = 0; row < figure.getRowCount(); row++) {
            for (int column = 0; column < figure.getColumnCount(); column++) {
                if (matr[row][column] != 0) {
                    matr[row - 1][column] = matr[row][column];
                    matr[row][column] = 0;
                }
            }
        }
        subFigure.decrementRow();
        log.debug("TOF: '{}' move UP force", typeOfFigure);
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
        log.debug("TOF: '{}' move RIGHT force", typeOfFigure);
    }

    private void rotateForce() {
        subFigure.update(figure);
        subFigure.transposeMatrClockWise();
        figure.copySubMatr(subFigure);
    }

    public void moveDown(RootGlass rootGlass) {
        lock.lock();
        try {
            if (isDownAvailable(rootGlass)) {
                moveDownForce();
            }
        } finally {
            lock.unlock();
        }
    }

    public void moveUp(RootGlass rootGlass) {
        lock.lock();
        try {
            if (isUpAvailable(rootGlass)) {
                moveUpForce();
            }
        } finally {
            lock.unlock();
        }
    }

    public void moveLeft(RootGlass rootGlass) {
        lock.lock();
        try {
            if (isLeftAvailable(rootGlass)) {
                moveLeftForce();
            }
        } finally {
            lock.unlock();
        }
    }

    public void moveRight(RootGlass rootGlass) {
        lock.lock();
        try {
            if (isRightAvailable(rootGlass)) {
                moveRightForce();
            }
        } finally {
            lock.unlock();
        }
    }

    public void rotate(RootGlass rootGlass) {
        lock.lock();
        try {
            if (isRotateAvailable(rootGlass)) {
                leftBeforeRotate();
                rotateForce();
                rotationAngle.rotate();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Метод для движа влево, если фигура плотничком в правом краю и ее длина больше ширины
     */
    private void leftBeforeRotate() {
        int countLeftMove = subFigure.getRowCount() - (figure.getColumnCount() - subFigure.getColumnCoord());
        if (countLeftMove > 0) {
            for (int i = 0; i < countLeftMove; i++) {
                moveLeftForce();
            }
        }
    }

    public long getComeDownTime(int totalPoints) {
        if (FigureState.FALL.equals(state)) {
            return 20;
        }
        return comeDownTime.getComeDownTime(totalPoints);
    }

    private boolean isEmptyRow(int rowNum) {
        for (int column = 0; column < figure.getColumnCount(); column++) {
            if (figure.getMatr()[rowNum][column] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyColumn(int columnNum) {
        for (int row = 0; row < figure.getRowCount(); row++) {
            if (figure.getMatr()[row][columnNum] != 0) {
                return false;
            }
        }
        return true;
    }

    private int getMinRow() {
        for (int row = 0; row < figure.getRowCount(); row++) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MAX rowSubQuadrate is -1 !!!");
        return -1;
    }

    private int getMinColumn() {
        for (int column = 0; column < figure.getColumnCount(); column++) {
            if (!isEmptyColumn(column)) {
                return column;
            }
        }
        log.error("MIN columnSubQuadrate is -1 !!!");
        return -1;
    }

    private int getMaxRow() {
        for (int row = figure.getRowCount() - 1; row >= 0; row--) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MIN rowSubQuadrate is -1 !!!");
        return -1;
    }

    private int getMaxColumn() {
        for (int column = figure.getColumnCount() - 1; column >= 0; column--) {
            if (!isEmptyColumn(column)) {
                return column;
            }
        }
        log.error("MAX columnSubQuadrate is -1 !!!");
        return -1;
    }

    public TemplateOfFigure clone() {
        TemplateOfFigure tofClone = new TemplateOfFigure(figure.getRowCount(), figure.getColumnCount());
        tofClone.setFigure(figure.clone());
        tofClone.setSubFigure(subFigure.clone());
        tofClone.setTypeOfFigure(typeOfFigure);
        tofClone.setRotationAngle(rotationAngle.clone());
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

    public SubMatr getSubFigure() {
        return subFigure;
    }

    public void setSubFigure(SubMatr subFigure) {
        this.subFigure = subFigure;
    }

    public Matr getFigure() {
        return figure;
    }

    public void setFigure(Matr figure) {
        this.figure = figure;
    }

    public void setRotationAngle(RotationAngle rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
