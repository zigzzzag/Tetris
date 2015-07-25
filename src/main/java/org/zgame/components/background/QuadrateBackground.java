package org.zgame.components.background;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by mnikiforov on 25.07.2015.
 */
public class QuadrateBackground implements Background {

    @Override
    public BufferedImage getBackground(int width,int height) {
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int sizeQuad = 256;
        Graphics2D g2 = resultImage.createGraphics();
        for (int row = 0; row < width; row += sizeQuad) {
            for (int column = 0; column < height; column += sizeQuad) {
                g2.setColor(new Color(50 + new Random().nextInt(100), 160, 50 + 50 + new Random().nextInt(100)));
                g2.fillRect(row, column, sizeQuad, sizeQuad);
            }
        }
        return resultImage;
    }

    public static void main(String[] args) {
        Background background = new QuadrateBackground();
        int[] size = BackgroundImageTest.getSize();
        BackgroundImageTest.backgroundTest(background.getBackground(size[0], size[1]));
    }
}
