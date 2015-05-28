/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.components;

import org.zgame.tetris.GComponent;
import org.zgame.tetris.GComponentClickAction;
import org.zgame.tetris.ScreenClickEvent;
import org.zgame.utils.ImageCache;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class RootComponent extends GComponent implements GComponentClickAction {

    private BufferedImage background;

    public RootComponent() {
        super("ROOT");
        background = ImageCache.getInstance().getImage("background.gif");
    }

    @Override
    public boolean actionClick(GComponent target, ScreenClickEvent event) {
        return false;
    }

    @Override
    protected void renderComponentOffset(int offsetX, int offsetY, Graphics2D gr) {
        gr.drawImage(background, 0, 0, null);
    }
}
