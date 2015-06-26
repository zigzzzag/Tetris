package org.zgame.tetris.component;

import org.zgame.utils.Constants;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;


/**
 * Created by mnikiforov on 31.05.2015.
 */
public class FigurePaint {

    public static void gradientFigure(Graphics2D gr2d, Color color1, Color color2, int x, int y) {
        int cnt = 30;
        GradientPaint gp = new GradientPaint(x, y, color1, x + cnt, y + cnt, color2, false);
        gr2d.setPaint(gp);
        gr2d.fillRect(x, y, cnt, cnt);

        gp = new GradientPaint(x + 2, y + 2, color2, x + cnt - 2, y + cnt - 2, color1, false);
        gr2d.setPaint(gp);
        gr2d.fillRect(x + 2, y + 2, cnt - 4, cnt - 4);
    }

    public static Color darkColor(int index) {
        switch (index) {
            case 1: {
                return new Color(0, 0, 139);
            }
            case 2: {
                return new Color(0, 139, 0);
            }
            case 3: {
                return new Color(139, 0, 0);
            }
            case 4: {
                return new Color(199, 21, 133);//dark pink
            }
            case 5: {
                return new Color(255, 165, 0);//orange
            }
            case 6: {
                return Color.darkGray;
            }
            case 7: {
                return new Color(255, 120, 0);//dark orange
            }
            case 8: {
                return new Color(50, 205, 50);//limegreen
            }
        }
        return new Color(0, 0, 0);
    }

    public static Color lightColor(int index) {
        switch (index) {
            case 1: {
                return new Color(0, 0, 205);
            }
            case 2: {
                return new Color(0, 205, 100);
            }
            case 3: {
                return new Color(205, 0, 0);
            }
            case 4: {
                return new Color(218, 112, 214);
            }
            case 5: {
                return new Color(255, 255, 0);//yellow
            }
            case 6: {
                return Color.gray;
            }
            case 7: {
                return new Color(255, 165, 0);//orange
            }
            case 8: {
                return new Color(0, 255, 0);//lime
            }
        }
        return new Color(0, 0, 0);
    }

    public static int converFromIndexColumn(int column) {
        return column * Constants.quadrateSize + Constants.indentLeft;
    }

    public static int converFromIndexRow(int row) {
        return row * Constants.quadrateSize + Constants.indentUp;
    }
}
