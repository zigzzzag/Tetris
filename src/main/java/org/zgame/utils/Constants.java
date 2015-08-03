package org.zgame.utils;

import java.awt.Color;

/**
 * Created by SBT-Nikiforov-MO on 26.05.2015.
 */
public final class Constants {

    public static final int MATR_COLUMN = 10;
    public static final int MATR_ROW = 20;
    public static int QUADRATE_SIZE = 10;
    public static int INDENT_UP = 100;
    public static int INDENT_LEFT = 500;

    public static Color alphaBlack = new Color(0, 0, 0, 0x80);
    public static Color alphaBlack2 = new Color(0, 0, 0, 0x99);
    public static Color alphaBlack3 = new Color(0, 0, 0, 108);
    public static Color alphaShadow = new Color(0, 0, 0, 140);

    public static byte[][] EMPTY_ROOT_GLASS_MATR = new byte[][]{
            //    0  1  2  3  4  5  6  7  8  9
            /*0*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*12*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
}
