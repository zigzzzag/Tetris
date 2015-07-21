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
        GradientPaint gp = new GradientPaint(x, y, color1, x + Constants.QUADRATE_SIZE, y + Constants.QUADRATE_SIZE, color2, false);
        gr2d.setPaint(gp);
        gr2d.fillRect(x, y, Constants.QUADRATE_SIZE, Constants.QUADRATE_SIZE);

        gp = new GradientPaint(x + 2, y + 2, color2, x + Constants.QUADRATE_SIZE - 2, y + Constants.QUADRATE_SIZE - 2, color1, false);
        gr2d.setPaint(gp);
        gr2d.fillRect(x + 2, y + 2, Constants.QUADRATE_SIZE - 4, Constants.QUADRATE_SIZE - 4);
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
        return column * Constants.QUADRATE_SIZE + Constants.INDENT_LEFT;
    }

    public static int converFromIndexRow(int row) {
        return row * Constants.QUADRATE_SIZE + Constants.INDENT_UP;
    }
}
