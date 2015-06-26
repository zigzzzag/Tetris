package org.zgame.utils;

import org.zgame.tetris.Main;

import java.awt.Color;

/**
 * Created by SBT-Nikiforov-MO on 26.05.2015.
 */
public final class Constants {

    public static final byte matrX = 10;
    public static final byte matrY = 20;
    public static final int quadrateSize = 30;
    public static int indentUp = 100;
    public static int indentLeft = Main.getInstance().getWidth() / 2 - 5 * quadrateSize;

    public static Color alphaBlack = new Color(0, 0, 0, 0x80);
    public static Color alphaBlack2 = new Color(0, 0, 0, 0x99);
    public static Color alphaBlack3 = new Color(0, 0, 0, 140);
    public static Color alphaShadow = new Color(0, 0, 0, 140);
}
