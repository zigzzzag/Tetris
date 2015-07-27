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
        root.setWidth(Main.getScreen().getWidth());
        root.setHeight(Main.getScreen().getHeight());

        Thread t = new Thread(gameContext);
        t.start();

        this.gameContext = gameContext;

        closeButton = new ImageButton("CLOSE", "blueButton.png", "ВЫХОД");
        closeButton.setComponentPosX(Main.getScreen().getWidth() - closeButton.getWidth() - 20);
        closeButton.setComponentPosY(20);
        root.appendChildElement(closeButton);
        closeButton.setAction(this);

        pauseButton = new ImageButton("PAUSE", "pauseButton.png", "");
        pauseButton.setComponentPosX(pauseButton.getWidth() + 20);
        pauseButton.setComponentPosY(20);
        root.appendChildElement(pauseButton);
        pauseButton.setAction(this);

        playButton = new ImageButton("PLAY", "playButton.png", "");
        playButton.setComponentPosX(2 * pauseButton.getWidth() + 40);
        playButton.setComponentPosY(20);
        root.appendChildElement(playButton);
        playButton.setAction(this);

        label = new TextCenterComponent("LABEL", "Tetris game", "Arial-bold-48", Color.BLACK, root.getWidth() / 2, 50);
        root.appendChildElement(label);

        timeTCC = new TextCenterComponent("LABEL", "", "Arial-normal-12", Color.BLACK, 50, 250);
        root.appendChildElement(timeTCC);

        countTCC = new TextCenterComponent("COUNT", "Счет: 0", "Arial-bold-48", Color.white, root.getWidth() - 200, 200);
        root.appendChildElement(countTCC);
    }

    private List<ParticleEffect> particles = new ArrayList<>();

    @Override
    public void render(Graphics2D gr2d) {
        root.render(gr2d);

        gr2d.setFont(Font.decode("Arial-norm-14"));

        gr2d.setColor(Color.ORANGE);
        gr2d.drawString("FPS: " + Main.getScreen().getFpsData().getFPS(), 180, 220);

        gr2d.setColor(Constants.alphaBlack3);
        gr2d.fillRoundRect(Constants.INDENT_LEFT - 10, Constants.INDENT_UP - 10, Constants.QUADRATE_SIZE * 10 + 20, Constants.QUADRATE_SIZE * 20 + 20, 10, 10);

        gr2d.setColor(Color.BLACK);
        gr2d.drawRect(Constants.INDENT_LEFT, Constants.INDENT_UP, Constants.QUADRATE_SIZE * 10, Constants.QUADRATE_SIZE * 20);

        gameContext.paint(gr2d);

//        if (start < 0) {
//            start = 0;
//            particles.clear();
//        } else {
//            start--;
//            for (ParticleEffect p : particles) {
//                p.render(gr2d);
//            }
//        }
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
            Main.getScreen().setCurrentStage(new WelcomeStage());
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
