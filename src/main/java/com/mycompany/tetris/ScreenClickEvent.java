/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tetris;

/**
 *
 * @author user
 */
public class ScreenClickEvent extends StageEvent {

    private int X = 0;
    private int Y = 0;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public ScreenClickEvent(int mouseCoordX, int mouseCoordY) {
        super(EventTypes.SCREEN_CLICK);
        X = mouseCoordX;
        Y = mouseCoordY;
    }
}
