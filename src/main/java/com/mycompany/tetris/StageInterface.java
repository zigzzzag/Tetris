/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tetris;

import java.awt.Graphics2D;

/**
 *
 * @author user
 */
public interface StageInterface{

    public void render(Graphics2D gr2d);
    
    public void processEvent(StageEvent evt);
}
