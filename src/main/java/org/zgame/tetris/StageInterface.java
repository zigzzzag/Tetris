/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris;

import java.awt.*;

/**
 * @author user
 */
public interface StageInterface {

    public void render(Graphics2D gr2d);

    public void processEvent(StageEvent evt);
}
