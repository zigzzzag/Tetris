/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.stage;

import org.zgame.components.ImageButton;
import org.zgame.components.RootComponent;
import org.zgame.components.TextCenterComponent;
import org.zgame.tetris.GComponent;
import org.zgame.tetris.GComponentClickAction;
import org.zgame.tetris.Main;
import org.zgame.tetris.ScreenClickEvent;
import org.zgame.tetris.StageEvent;
import org.zgame.tetris.StageInterface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;

/**
 *
 * @author user
 */
public class WelcomeStage implements StageInterface, GComponentClickAction {

    private RootComponent root;
    private TextCenterComponent label;
    private ImageButton exitGame;
    private ImageButton newGame;
    private ImageButton record;
    private ImageButton instructions;
    private TetrisStage tetrisStage;
    private static final int offset = 100;

    public WelcomeStage() {
        root = new RootComponent();
        root.setWidth(Main.getInstance().getWidth());
        root.setHeight(Main.getInstance().getHeight());

        label = new TextCenterComponent("LABEL", "Welcome to the Tetris", "Arial-bold-48", Color.white, root.getWidth() / 2, 50);
        root.appendChildElement(label);

        newGame = new ImageButton("NEW_GAME", "green_button.png", "NEW GAME");
        newGame.setComponentPosX(root.getWidth() / 2 - newGame.getWidth() / 2);
        newGame.setComponentPosY(offset);
        root.appendChildElement(newGame);
        newGame.setAction((GComponentClickAction) this);

        record = new ImageButton("RECORDS", "welcome_button.png", "RECORDS");
        record.setComponentPosX(root.getWidth() / 2 - record.getWidth() / 2);
        record.setComponentPosY(offset + record.getHeight());
        record.setAction((GComponentClickAction) this);
        root.appendChildElement(record);

        instructions = new ImageButton("INSTRUCTION", "welcome_button.png", "INSTRUCTION");
        instructions.setComponentPosX(root.getWidth() / 2 - instructions.getWidth() / 2);
        instructions.setComponentPosY(offset + instructions.getHeight() * 2);
        root.appendChildElement(instructions);
        instructions.setAction((GComponentClickAction) this);

        exitGame = new ImageButton("EXIT_GAME", "welcome_button.png", "EXIT");
        exitGame.setComponentPosX(root.getWidth() / 2 - exitGame.getWidth() / 2);
        exitGame.setComponentPosY(offset + instructions.getHeight() * 3);
        root.appendChildElement(exitGame);
        exitGame.setAction((GComponentClickAction) this);
        
        tetrisStage = new TetrisStage();
    }

    @Override
    public void render(Graphics2D gr2d) {
        root.render(gr2d);
    }

    @Override
    public void processEvent(StageEvent evt) {
        root.processClickAction((ScreenClickEvent) evt);
    }

    @Override
    public boolean actionClick(GComponent target, ScreenClickEvent event) {
        final String action = target.getComponentName();

        if ("NEW_GAME".equals(action)) {
            tetrisStage.setGameOver(false);
            Main.getInstance().setCurrentStage(tetrisStage);
        }

        if ("RECORDS".equals(action)) {
            Main.getInstance().setCurrentStage(new RecordsStage());
        }

        if ("EXIT_GAME".equals(action)) {
            Main.getInstance().dispatchEvent(new WindowEvent(Main.getInstance(), WindowEvent.WINDOW_CLOSING));
        }
        return true;
    }
}
