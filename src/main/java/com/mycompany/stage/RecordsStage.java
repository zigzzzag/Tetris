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
import com.mycompany.utils.Record;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author user
 */
public class RecordsStage implements StageInterface, GComponentClickAction {

    private RootComponent root;
    private ImageButton closeButton;
    private TextCenterComponent label;

    public RecordsStage() {
        root = new RootComponent();
        root.setWidth(Main.getInstance().getWidth());
        root.setHeight(Main.getInstance().getHeight());

        label = new TextCenterComponent("LABEL", "BEST RESULTS", "Arial-bold-48",
                Color.BLACK, root.getWidth() / 2, 50);
        root.appendChildElement(label);

        List<Record> record = Record.getRecord();
        int i = 0;
        Color defaultColor = new Color(255, 215, 150);
        for (Record rec : record) {
            i++;

            TextCenterComponent tccFIO = new TextCenterComponent("", rec.getFIO(),
                    "Arial-bold-48", defaultColor, root.getWidth() / 4, 50 * i + 100);
            TextCenterComponent tccCount = new TextCenterComponent("", rec.getCount() + "",
                    "Arial-bold-48", defaultColor, root.getWidth() / 4 * 3, 50 * i + 100);
            switch (i) {
                case 1: {
                    Color gold = new Color(255, 215, 0);
                    tccCount.setColor(gold);
                    tccFIO.setColor(gold);
                    break;
                }
                case 2: {
                    Color gold2 = new Color(255, 215, 40);
                    tccCount.setColor(gold2);
                    tccFIO.setColor(gold2);
                    break;
                }
                case 3: {
                    Color gold3 = new Color(255, 215, 80);
                    tccCount.setColor(gold3);
                    tccFIO.setColor(gold3);
                    break;
                }
            }
            root.appendChildElement(tccCount);
            root.appendChildElement(tccFIO);
        }

        closeButton = new ImageButton("CLOSE", "blueButton.png", "EXIT");
        closeButton.setComponentPosX(root.getWidth() - closeButton.getWidth() - 20);
        closeButton.setComponentPosY(20);
        closeButton.setAction((GComponentClickAction) this);
        root.appendChildElement(closeButton);
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
        if ("CLOSE".equals(action)) {
            Main.getInstance().setCurrentStage(new WelcomeStage());
        }
        return true;
    }
}
