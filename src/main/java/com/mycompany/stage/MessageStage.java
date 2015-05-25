/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stage;

import com.mycompany.components.ImageButton;
import com.mycompany.components.RootComponent;
import com.mycompany.components.TextCenterComponent;
import com.mycompany.tetris.GComponent;
import com.mycompany.tetris.GComponentClickAction;
import com.mycompany.tetris.Main;
import com.mycompany.tetris.ScreenClickEvent;
import com.mycompany.tetris.StageEvent;
import com.mycompany.tetris.StageInterface;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class MessageStage implements StageInterface, GComponentClickAction {

    private static MessageStage instance;
    private StageInterface okStage;
    private StageInterface cancelStage;
    private ImageButton okButton;
    private ImageButton cancelButton;
    private TextCenterComponent messageTCC;
    private Color alphaBlack = new Color(0, 0, 0, 0x80);
    private Color alphaBlack2 = new Color(0, 0, 0, 0x80);
    int w = 500;
    int h = 200;
    int sw = Main.getInstance().getWidth();
    int sh = Main.getInstance().getHeight();
    int roundRadius = 20;
    int widthBorder = 10;
    private RootComponent root;
    private BufferedImage preImage = new BufferedImage(Main.getInstance().getWidth(), Main.getInstance().getHeight(), BufferedImage.TYPE_INT_ARGB);

    public void setOkStage(StageInterface okStage) {
        this.okStage = okStage;
    }

    public void setCancelStage(StageInterface cancelStage) {
        this.cancelStage = cancelStage;
    }

    public static MessageStage getInstance() {
        if (instance == null) {
            instance = new MessageStage();
        }
        return instance;
    }

    private MessageStage() {
        root = new RootComponent();
        root.setWidth(sw);
        root.setHeight(sh);

        messageTCC = new TextCenterComponent("MESSAGE_TCC", "Вы уверены?", "Arial-bold-48", Color.BLACK, sw / 2, sh / 2);
//        root.appendChildElement(messageTCC);

        okButton = new ImageButton("OK", "close_button.png", "Ок");
        okButton.setComponentPosX(sw / 2 - okButton.getWidth() - 10);
        okButton.setComponentPosY(sh / 2);
        okButton.setAction((GComponentClickAction) this);
        root.appendChildElement(okButton);

        cancelButton = new ImageButton("CANCEL", "close_button.png", "Отмена");
        cancelButton.setAction((GComponentClickAction) this);
        cancelButton.setComponentPosX(sw / 2 + cancelButton.getWidth() + 10);
        cancelButton.setComponentPosY(sh / 2);
        root.appendChildElement(cancelButton);
    }

    @Override
    public void render(Graphics2D gr2d) {
        root.render(gr2d);

        if (cancelStage != null) {
            cancelStage.render((Graphics2D) preImage.getGraphics());
            gr2d.drawImage(preImage, null, 0, 0);
            gr2d.setColor(alphaBlack);
            gr2d.fillRect(0, 0, Main.getInstance().getWidth(), Main.getInstance().getHeight());
        } else {
            gr2d.setColor(Color.red);
            gr2d.fillRect(0, 0, Main.getInstance().getWidth(), Main.getInstance().getHeight());
        }

        gr2d.setColor(alphaBlack2);
        gr2d.fillRoundRect((sw - w) / 2, (sh - h) / 2, w, h, roundRadius, roundRadius);
        gr2d.setColor(Color.WHITE);
        gr2d.fillRoundRect((sw - w) / 2 + widthBorder, (sh - h) / 2 + widthBorder, w - 2 * widthBorder, h - 2 * widthBorder, roundRadius, roundRadius);

        messageTCC.render(gr2d);
    }

    @Override
    public void processEvent(StageEvent evt) {
        root.processClickAction((ScreenClickEvent) evt);
    }

    @Override
    public boolean actionClick(GComponent target, ScreenClickEvent event) {
        final String action = target.getComponentName();

        if ("OK".equals(action)) {
            Main.getInstance().setCurrentStage(okStage);
        }

        if ("CANCEL".equals(action)) {
            Main.getInstance().setCurrentStage(cancelStage);
        }

        return true;
    }
}
