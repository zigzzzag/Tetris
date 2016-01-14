package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.stage.GameOverStage;
import org.zgame.stage.TetrisStage;
import org.zgame.tetris.Main;
import org.zgame.tetris.component.matr.Matr;
import org.zgame.utils.Constants;
import org.zgame.utils.ParticleEffect;
import org.zgame.utils.Record;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mnikiforov on 31.05.2015.
 */
public class GameContext implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(GameContext.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private TemplateOfFigure currentFigure;
    private TemplateOfFigure nextFigure;
    private RootGlass rootGlass;
    private boolean gameOver = false;
    private int pointsNow;
    private int pointsAll;

    public static GameContext INSTANCE = new GameContext();

    private GameContext() {
        rootGlass = new RootGlass();

        currentFigure = randomFigure();
        nextFigure = randomFigure();
    }

    @Override
    public void run() {
        while (!gameOver) {
            try {
                Thread.sleep(currentFigure.getComeDownTime(pointsAll));
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e.getCause());
            }
            if (currentFigure.getState().equals(FigureState.NORMAL)) {
                currentFigure.moveDown(rootGlass);
            }
        }

        gameOver = true;
        log.info("GAME OVER!!!");

        List<Record> recordList = Record.getRecord();
        Record record = new Record();
        record.setCount(pointsAll);
        record.setFIO(Main.getName());
        recordList.add(record);
        Record.setRecord(recordList);

        GameOverStage gos = new GameOverStage(this);
//        gos.setReturnStage(this);
        Main.getScreen().setCurrentStage(gos);
    }

    //TODO refactor
    private void nextStep() {
        for (int row = 0; row < Constants.MATR_ROW; row++) {
            for (int column = 0; column < Constants.MATR_COLUMN; column++) {
                rootGlass.getFilledGlass().getMatr()[row][column] += currentFigure.getFigure().getMatr()[row][column];
            }
        }


        for (int row = 0; row < rootGlass.getRowCount(); row++) {
            for (int column = 0; column < rootGlass.getColumnCount(); column++) {
                if (rootGlass.getFilledGlass().getMatr()[row][column] == 0) {
                    break;
                }
                //последняя не нулевая, значит вся линия заполнена
                if (column == rootGlass.getColumnCount() - 1) {
                    rootGlass.deleteFullLine(row);
                    pointsNow++;
                }
            }
        }
        if (pointsNow != 0) {
            pointsAll += getDeleteLinePoints(pointsNow);
//                    countTCC.setText("Счет: " + pointsAll);
        }


        currentFigure = nextFigure;
        nextFigure = randomFigure();
        pointsNow = 0;
    }

    public void fallCurrentFigure() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (currentFigure.getState().equals(FigureState.FALL) && currentFigure.isDownAvailable(rootGlass)) {
                    currentFigure.moveDown(rootGlass);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
                nextStep();
                for (int t = 0; t < 10; t++) {
                    TetrisStage.particles.add(new ParticleEffect(Matr.converFromIndexColumn(t), Matr.converFromIndexColumn(t)));
                }
                TetrisStage.renderParticleEffects = ParticleEffect.TIME;
            }
        });
    }

    public void paint(Graphics2D gr2d) {
        rootGlass.paintRootGlass(gr2d);
        currentFigure.paintFigureShadow(gr2d);
        currentFigure.paintFigure(gr2d);
        nextFigure.paintFigureNext(gr2d);
    }

    public void gameReset() {
        for (int row = 0; row < Constants.MATR_ROW; row++) {
            for (int column = 0; column < Constants.MATR_COLUMN; column++) {
                rootGlass.getFilledGlass().getMatr()[row][column] = 0;
            }
        }
//        pointsAll = 0;
        gameOver = true;
        currentFigure = randomFigure();
    }

    private TemplateOfFigure randomFigure() {
        TemplateOfFigure result = new TemplateOfFigure(FigureType.randomType());
        result.randomizeColor();
        int rotateRandom = new Random().nextInt(4);
        for (int i = 0; i < rotateRandom; i++) {
            result.rotate(rootGlass);
        }
        return result;
    }

    public int getDeleteLinePoints(int points) {
        switch (points) {
            case 1: {
                return 100;
            }
            case 2: {
                return 300;
            }
            case 3: {
                return 700;
            }
            case 4: {
                return 1500;
            }
        }
        return 0;
    }

    public TemplateOfFigure getCurrentFigure() {
        return currentFigure;
    }

    public RootGlass getRootGlass() {
        return rootGlass;
    }

    public void setRootGlass(RootGlass rootGlass) {
        this.rootGlass = rootGlass;
    }
}
