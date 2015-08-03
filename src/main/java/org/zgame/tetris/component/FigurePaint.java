package org.zgame.tetris.component;

import org.zgame.utils.Constants;

import java.awt.*;


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


}
