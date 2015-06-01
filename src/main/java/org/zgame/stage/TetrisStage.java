/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.components.ImageButton;
import org.zgame.components.RootComponent;
import org.zgame.components.TextCenterComponent;
import org.zgame.tetris.GComponent;
import org.zgame.tetris.GComponentClickAction;
import org.zgame.tetris.Main;
import org.zgame.tetris.ScreenClickEvent;
import org.zgame.tetris.StageEvent;
import org.zgame.tetris.StageInterface;
import org.zgame.tetris.component.FigureState;
import org.zgame.tetris.component.GameContext;
import org.zgame.utils.Constants;
import org.zgame.utils.ParticleEffect;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 */
public class TetrisStage implements StageInterface, GComponentClickAction, KeyListener {

    private static final Logger log = LoggerFactory.getLogger(TetrisStage.class);
    private RootComponent root;
    private ImageButton closeButton;
    private ImageButton pauseButton;
    private ImageButton playButton;
    private TextCenterComponent label;
    private TextCenterComponent timeTCC;
    private TextCenterComponent countTCC;
    private GameContext gameContext;

    public TetrisStage(GameContext gameContext) {
        root = new RootComponent();
        root.setWidth(Main.getInstance().getWidth());
        root.setHeight(Main.getInstance().getHeight());

        Thread t = new Thread(gameContext);
        t.start();

        this.gameContext = gameContext;

        closeButton = new ImageButton("CLOSE", "blueButton.png", "ВЫХОД");
        closeButton.setComponentPosX(Main.getInstance().getWidth() - closeButton.getWidth() - 20);
        closeButton.setComponentPosY(20);
        root.appendChildElement(closeButton);
        closeButton.setAction((GComponentClickAction) this);

        pauseButton = new ImageButton("PAUSE", "pauseButton.png", "");
        pauseButton.setComponentPosX(pauseButton.getWidth() + 20);
        pauseButton.setComponentPosY(20);
        root.appendChildElement(pauseButton);
        pauseButton.setAction((GComponentClickAction) this);

        playButton = new ImageButton("PLAY", "playButton.png", "");
        playButton.setComponentPosX(2 * pauseButton.getWidth() + 40);
        playButton.setComponentPosY(20);
        root.appendChildElement(playButton);
        playButton.setAction((GComponentClickAction) this);

        label = new TextCenterComponent("LABEL", "Tetris game", "Arial-bold-48", Color.BLACK, root.getWidth() / 2, 50);
        root.appendChildElement(label);

        timeTCC = new TextCenterComponent("LABEL", "", "Arial-normal-12", Color.BLACK, 50, 250);
        root.appendChildElement(timeTCC);

        countTCC = new TextCenterComponent("COUNT", "Счет: 0", "Arial-bold-48", Color.white, root.getWidth() - 200, 200);
        root.appendChildElement(countTCC);
    }

    private List<ParticleEffect> particles = new ArrayList<ParticleEffect>();
    private int start = 0;

    private long timeNow;
    private long timeLast;
    private long fullTimeLast = System.currentTimeMillis();
    private long frameAll;
    private long fpsMiddle;
    private long fpsLastSecond;
    private long fpsLastSecondSave;

    @Override
    public void render(Graphics2D gr2d) {
        timeLast = timeNow;
        timeNow += System.currentTimeMillis() - fullTimeLast;
        fullTimeLast = System.currentTimeMillis();

        frameAll++;
        fpsMiddle = frameAll * 1000 / (timeNow == 0 ? 1 : timeNow);

        fpsLastSecond++;

        timeTCC.setText("Время: " + timeNow);
        root.render(gr2d);

        gr2d.setFont(Font.decode("Arial-norm-14"));

        gr2d.setColor(Color.ORANGE);
        gr2d.drawString("FPS_MIDDLE: " + fpsMiddle, 180, 220);

        if (timeLast % 1000 > timeNow % 1000) {
            fpsLastSecondSave = fpsLastSecond;
            fpsLastSecond = 0;
        }
        gr2d.drawString("FPS_LAST_SECOND: " + fpsLastSecondSave, 180, 240);

        gr2d.setColor(Constants.alphaBlack3);
        gr2d.fillRoundRect(Constants.indentLeft - 10, Constants.indentUp - 10, Constants.quadrateSize * 10 + 20, Constants.quadrateSize * 20 + 20, 10, 10);

        gr2d.setColor(Color.BLACK);
        gr2d.drawRect(Constants.indentLeft, Constants.indentUp, Constants.quadrateSize * 10, Constants.quadrateSize * 20);

        gameContext.paint(gr2d);

        if (start < 0) {
            start = 0;
            particles.clear();
        } else {
            start--;
            for (ParticleEffect p : particles) {
                p.render(gr2d);
            }
        }
    }

    @Override
    public void processEvent(StageEvent evt) {
        root.processClickAction((ScreenClickEvent) evt);
    }

    @Override
    public boolean actionClick(GComponent target, ScreenClickEvent event) {
        final String action = target.getComponentName();
        if ("CLOSE".equals(action)) {
            gameContext.gameReset();
            Main.getInstance().setCurrentStage(new WelcomeStage());
        }

        if ("PAUSE".equals(action)) {
//            pause = true;
        }

        if ("PLAY".equals(action)) {
//            pause = false;
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: {
                gameContext.getCurrentFigure().moveRight(gameContext.getRootGlass());
                break;
            }
            case KeyEvent.VK_LEFT: {
                gameContext.getCurrentFigure().moveLeft(gameContext.getRootGlass());
                break;
            }
            case KeyEvent.VK_DOWN: {
                gameContext.getCurrentFigure().moveDown(gameContext.getRootGlass());
                break;
            }
            case KeyEvent.VK_UP: {
                gameContext.getCurrentFigure().rotate(gameContext.getRootGlass());
                break;
            }
            case KeyEvent.VK_SPACE: {
                //downing = true;
                gameContext.getCurrentFigure().setState(FigureState.FALL);
                gameContext.fallCurrentFigure();
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
