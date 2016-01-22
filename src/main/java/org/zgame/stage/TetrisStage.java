/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.stage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.components.ImageButton;
import org.zgame.components.RootComponent;
import org.zgame.components.TextCenterComponent;
import org.zgame.components.effects.FireworkEffect;
import org.zgame.tetris.GComponent;
import org.zgame.tetris.GComponentClickAction;
import org.zgame.tetris.Main;
import org.zgame.tetris.ScreenClickEvent;
import org.zgame.tetris.StageEvent;
import org.zgame.tetris.StageInterface;
import org.zgame.tetris.component.FigureState;
import org.zgame.tetris.component.GameContext;
import org.zgame.tetris.component.TemplateOfFigure;
import org.zgame.utils.Constants;

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

    public TetrisStage() {
        root = new RootComponent();
        root.setWidth(Main.getScreen().getWidth());
        root.setHeight(Main.getScreen().getHeight());

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

    public static FireworkEffect fireworkEffect = new FireworkEffect(10);

    @Override
    public void render(Graphics2D gr2d) {
        root.render(gr2d);

        gr2d.setFont(Font.decode("Arial-norm-14"));

        gr2d.setColor(Color.BLACK);
        gr2d.drawString("FPS: " + Main.getScreen().getFpsData().getFPS(), 180, 220);

        gr2d.setColor(Constants.alphaBlack3);
        gr2d.fillRoundRect(Constants.INDENT_LEFT - 10, Constants.INDENT_UP - 10, Constants.QUADRATE_SIZE * 10 + 20, Constants.QUADRATE_SIZE * 20 + 20, 10, 10);

        gr2d.setColor(Color.BLACK);
        gr2d.drawRoundRect(Constants.INDENT_LEFT, Constants.INDENT_UP, Constants.QUADRATE_SIZE * 10, Constants.QUADRATE_SIZE * 20, 10, 10);

        GameContext.INSTANCE.paint(gr2d);

        fireworkEffect.render(gr2d);
    }

    @Override
    public void processEvent(StageEvent evt) {
        root.processClickAction((ScreenClickEvent) evt);
    }

    @Override
    public boolean actionClick(GComponent target, ScreenClickEvent event) {
        final String action = target.getComponentName();
        if ("CLOSE".equals(action)) {
            GameContext.INSTANCE.gameReset();
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
        TemplateOfFigure currentFigure = GameContext.INSTANCE.getCurrentFigure();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: {
                if (currentFigure.getState().equals(FigureState.NORMAL)) {
                    currentFigure.moveRight(GameContext.INSTANCE.getRootGlass());
                }
                break;
            }
            case KeyEvent.VK_LEFT: {
                if (currentFigure.getState().equals(FigureState.NORMAL)) {
                    currentFigure.moveLeft(GameContext.INSTANCE.getRootGlass());
                }
                break;
            }
            case KeyEvent.VK_DOWN: {
                if (currentFigure.getState().equals(FigureState.NORMAL)) {
                    currentFigure.moveDown(GameContext.INSTANCE.getRootGlass());
                }
                break;
            }
            case KeyEvent.VK_UP: {
                if (currentFigure.getState().equals(FigureState.NORMAL)) {
                    currentFigure.rotate(GameContext.INSTANCE.getRootGlass());
                }
                break;
            }
            case KeyEvent.VK_SPACE: {
                if (currentFigure.getState().equals(FigureState.NORMAL)) {
                    currentFigure.setState(FigureState.FALL);
                    currentFigure.fallCurrentFigure();
                }
                break;
            }
            case KeyEvent.VK_F: {
                fireworkEffect.startEffect(0);
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
