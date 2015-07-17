package org.zgame.tetris.multi_thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.RootGlass;
import org.zgame.tetris.component.TemplateOfFigure;

import java.util.concurrent.CountDownLatch;

/**
 * Created by SBT-Nikiforov-MO on 06.07.2015.
 */
public class RunnableForMove implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(RunnableForMove.class);
    private TemplateOfFigure tof;
    private RootGlass rootGlass;
    private Move moveType;
    private CountDownLatch latch;

    public RunnableForMove(TemplateOfFigure tof, RootGlass rootGlass, Move moveType) {
        this.tof = tof;
        this.rootGlass = rootGlass;
        this.moveType = moveType;
    }

    public RunnableForMove latch(CountDownLatch latch) {
        this.latch = latch;
        return this;
    }

    @Override
    public void run() {
        if (latch != null) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }

        switch (moveType) {
            case LEFT: {
                tof.moveLeft(rootGlass);
                break;
            }
            case RIGHT: {
                tof.moveRight(rootGlass);
                break;
            }
            case DOWN: {
                tof.moveDown(rootGlass);
                break;
            }
            case UP: {
                tof.moveUp(rootGlass);
                break;
            }
            default: {
                log.warn("moveType not defined!");
            }
        }
    }
}
