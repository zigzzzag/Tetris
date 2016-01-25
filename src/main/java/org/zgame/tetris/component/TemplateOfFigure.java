/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.comedowntime.ComeDownTime;
import org.zgame.tetris.component.comedowntime.ComeDownTimeImpl;
import org.zgame.tetris.component.matr.Matr;
import org.zgame.tetris.component.matr.MatrUtils;
import org.zgame.tetris.component.matr.ShadowMatr;
import org.zgame.tetris.component.matr.SubMatr;
import org.zgame.utils.Constants;

/**
 * @author user
 */
public class TemplateOfFigure {

    private static final Logger log = LoggerFactory.getLogger(TemplateOfFigure.class);
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(1);

    private Matr figure;
    private ShadowMatr figureShadow;
    private SubMatr subFigure;
    private int quadrateSize = Constants.QUADRATE_SIZE;
    private FigureType typeOfFigure;
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

        figureShadow = new ShadowMatr(figure.getRowCount(), figure.getColumnCount());
        figure.toShadowMatr(figureShadow);
    }

    public TemplateOfFigure rotationAngleInt(int angle) {
        this.rotationAngle.setAngle(angle);
        for (int rotateCount = 0; rotateCount < rotationAngle.getCountRotate(); rotateCount++) {
            rotateForce();
        }
        return this;
    }

    public void paintFigure(Graphics2D g2d) {
        for (int row = 0; row < figure.getRowCount(); row++) {
            for (int column = 0; column < figure.getColumnCount(); column++) {
                if (figure.getElement(row, column) != 0) {
                    FigurePaint.gradientFigure(g2d,
                            GradientColors.getLightColorByNum(figure.getElement(row, column)), GradientColors.getDarkColorByNum(figure.getElement(row, column)),
                            Matr.convertFromIndexColumn(column), Matr.convertFromIndexRow(row));
                }
            }
        }
    }

    public void paintFigureShadow(Graphics2D g2d) {
        for (int row = 0; row < figureShadow.getRowCount(); row++) {
            for (int column = 0; column < figureShadow.getColumnCount(); column++) {
                if (figureShadow.getElement(row, column) != 0) {
                    g2d.setColor(Constants.alphaShadow);
                    g2d.fillRect(Matr.convertFromIndexColumn(column), Matr.convertFromIndexRow(row), Constants.QUADRATE_SIZE, Constants.QUADRATE_SIZE);
                    g2d.setColor(Color.black);
                    g2d.drawRect(Matr.convertFromIndexColumn(column), Matr.convertFromIndexRow(row), Constants.QUADRATE_SIZE, Constants.QUADRATE_SIZE);
                }
            }
        }
    }

    public void paintFigureNext(Graphics2D g2d) {
        for (int row = 0; row < figure.getRowCount(); row++) {
            for (int column = 0; column < figure.getColumnCount(); column++) {
                if (figure.getElement(row, column) != 0) {
                    FigurePaint.gradientFigure(g2d,
                            GradientColors.getLightColorByNum(figure.getElement(row, column)), GradientColors.getDarkColorByNum(figure.getElement(row, column)),
                            Matr.convertFromIndexColumn(column) - 300, Matr.convertFromIndexRow(row));
                }
            }
        }
    }

    private void clear() {
        this.figure.clear();
    }

    public boolean isDownAvailable(RootGlass rootGlass) {
        return figure.isDownAvailable(rootGlass);
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

    private void correctPositionBeforeRotate() {
        leftBeforeRotate();
        upBeforeRotate();
    }

    private boolean isRotateAvailable(RootGlass rootGlass) {
        TemplateOfFigure tofRotate = this.clone();
        tofRotate.correctPositionBeforeRotate();
        tofRotate.rotationAngleInt(90);
        if (rootGlass.hasIntersectionWithMatr(tofRotate.getFigure().getMatr())) {
            log.debug("TOF: '{}' is not ROTATE available, because rootGlass.hasIntersectionWithFigure(tofRotate)",
                    typeOfFigure);
            return false;
        }
        return true;
    }

    private void moveDownForce() {
        figure.down();
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
                updateFigureShadow(rootGlass);
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
                updateFigureShadow(rootGlass);
            }
        } finally {
            lock.unlock();
        }
    }

    public void rotate(RootGlass rootGlass) {
        lock.lock();
        try {
            if (isRotateAvailable(rootGlass)) {
                correctPositionBeforeRotate();
                rotateForce();
                rotationAngle.rotate();
                updateFigureShadow(rootGlass);
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

    /**
     * Метод для движа вверх, если фигура плотничком внизу и ее ширина больше длины
     */
    private void upBeforeRotate() {
        int countUpMove = subFigure.getColumnCount() - (figure.getRowCount() - subFigure.getRowCoord());
        if (countUpMove > 0) {
            for (int i = 0; i < countUpMove; i++) {
                moveUpForce();
            }
        }
    }

    public void updateFigureShadow(RootGlass rootGlass) {
        MatrUtils.copyMatr(figure.getMatr(), figureShadow.getMatr());
        while (figureShadow.isDownAvailable(rootGlass)) {
            figureShadow.down();
        }
    }

    public long getComeDownTime(int totalPoints) {
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

    public int getMaxRow() {
        for (int row = figure.getRowCount() - 1; row >= 0; row--) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MAX rowSubQuadrate is -1 !!!");
        return -1;
    }

    public int getMinRow() {
        for (int row = 0; row < figure.getRowCount(); row++) {
            if (!isEmptyRow(row)) {
                return row;
            }
        }
        log.error("MIN rowSubQuadrate is -1 !!!");
        return -1;
    }

    public int getMinColumn() {
        for (int column = 0; column < figure.getColumnCount(); column++) {
            if (!isEmptyColumn(column)) {
                return column;
            }
        }
        log.error("MIN columnSubQuadrate is -1 !!!");
        return -1;
    }

    public int getMaxColumn() {
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
        tofClone.setFigureShadow(figureShadow.clone());
        tofClone.setSubFigure(subFigure.clone());
        tofClone.setTypeOfFigure(typeOfFigure);
        tofClone.setRotationAngle(rotationAngle.clone());
        return tofClone;
    }

    public void fallCurrentFigure() {
        EXECUTOR_SERVICE.submit(new Runnable() {
            @Override
            public void run() {
                while (state.equals(FigureState.FALL) && TemplateOfFigure.this.isDownAvailable(GameContext.INSTANCE.getRootGlass())) {
                    TemplateOfFigure.this.moveDown(GameContext.INSTANCE.getRootGlass());
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
                GameContext.INSTANCE.nextStep();
            }
        });
    }

    private void setColorInt(int colorInt) {
        this.figure.setAllNotNullElements((byte) colorInt);
    }

    public void randomizeColor() {
        setColorInt(new Random().nextInt(GradientColors.GRADIENT_COLORS_COUNT) + 1);
    }

    @Override
    public String toString() {
        return figure.hashCode() + " " + getTypeOfFigure().toString() + " " + hashCode() + ": " + Arrays.deepToString(figure.getMatr());
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

    public ShadowMatr getFigureShadow() {
        return figureShadow;
    }

    public void setFigureShadow(ShadowMatr figureShadow) {
        this.figureShadow = figureShadow;
    }

    public int getQuadrateSize() {
        return quadrateSize;
    }

    public void setQuadrateSize(int quadrateSize) {
        this.quadrateSize = quadrateSize;
    }
}
