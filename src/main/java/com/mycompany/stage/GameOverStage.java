/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stage;

import com.mycompany.components.ImageButton;
import com.mycompany.components.RootComponent;
import com.mycompany.tetris.GComponent;
import com.mycompany.tetris.GComponentClickAction;
import com.mycompany.tetris.Main;
import com.mycompany.tetris.ScreenClickEvent;
import com.mycompany.tetris.StageEvent;
import com.mycompany.tetris.StageInterface;
import com.mycompany.utils.Constants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class GameOverStage implements StageInterface, GComponentClickAction {

    private RootComponent root;
    private ImageButton okButton;
    int roundRadius = 20;
    int widthBorder = 10;
    int w = 600;
    int h = 300;
    int sw = Main.getInstance().getWidth();
    int sh = Main.getInstance().getHeight();
    private StageInterface returnStage;
    private BufferedImage preImage = new BufferedImage(Main.getInstance().getWidth(), Main.getInstance().getHeight(), BufferedImage.TYPE_INT_ARGB);

    public GameOverStage() {
        root = new RootComponent();
        root.setWidth(Main.getInstance().getWidth());
        root.setHeight(Main.getInstance().getHeight());

        okButton = new ImageButton("OK", "green_button.png", "OK");
        okButton.setAction((GComponentClickAction) this);
        okButton.setComponentPosY((sh + h) / 2 - 10 - okButton.getHeight());
        okButton.setComponentPosX(sw / 2 - okButton.getWidth() / 2);
    }

    public void setReturnStage(StageInterface returnStage) {
        this.returnStage = returnStage;
    }

    @Override
    public void render(Graphics2D gr2d) {
        if (returnStage != null) {
            returnStage.render((Graphics2D) preImage.getGraphics());
            gr2d.drawImage(preImage, null, 0, 0);
            gr2d.setColor(Constants.alphaBlack);
            gr2d.fillRect(0, 0, Main.getInstance().getWidth(), Main.getInstance().getHeight());
        } else {
            gr2d.setColor(Color.red);
            gr2d.fillRect(0, 0, Main.getInstance().getWidth(), Main.getInstance().getHeight());
        }

        gr2d.setColor(Constants.alphaBlack2);
        gr2d.fillRoundRect((sw - w) / 2 - widthBorder, (sh - h) / 2, w + 2 * widthBorder, h + 2 * widthBorder, roundRadius, roundRadius);
        gr2d.setColor(Color.WHITE);
        gr2d.fillRoundRect((sw - w) / 2, (sh - h) / 2 + widthBorder, w, h, roundRadius, roundRadius);

        okButton.render(gr2d);
    }

    @Override
    public void processEvent(StageEvent evt) {
        okButton.processClickAction((ScreenClickEvent) evt);
    }

    @Override
    public boolean actionClick(GComponent target, ScreenClickEvent event) {
        final String action = target.getComponentName();
        if ("OK".equals(action)) {
            Main.getInstance().setCurrentStage(new WelcomeStage());
            TetrisStage ts = (TetrisStage)returnStage;
            ts.gameReset();
//            TetrisStage.gameReset();
        }
        return true;
    }
}
