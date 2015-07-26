package org.zgame.components.background;

import org.zgame.utils.ImageCache;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mnikiforov on 26.07.2015.
 */
public class RedBrickBackground implements Background  {
    private static final int BRICK_WIDTH = 160;
    private static final int BRICK_HEIGHT = 41;
    private static final int BRICKS_COUNT = 12;
    private static final int CEMENT_SIZE = 1;
    private static final String BRICK_IMAGE_NAME = "RedBricks.png";

    private List<BufferedImage> parseBricksImage() {
        List<BufferedImage> result = new ArrayList<>();
        BufferedImage bricksImage = ImageCache.getInstance().getImage(BRICK_IMAGE_NAME);
        for (int i = 0; i < BRICKS_COUNT; i++) {
            BufferedImage brick = bricksImage.getSubimage(BRICK_WIDTH * (i % 3), BRICK_HEIGHT * (i / 3), BRICK_WIDTH, BRICK_HEIGHT);

            BufferedImage brickWithCement = new BufferedImage(BRICK_WIDTH + CEMENT_SIZE * 2, BRICK_HEIGHT + CEMENT_SIZE * 2, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = brickWithCement.createGraphics();
            Color cementColor = new Color(27, 27, 27);
            g2.setColor(cementColor);
            g2.fillRect(0, 0, brickWithCement.getWidth(), brickWithCement.getHeight());
            g2.drawImage(brick, null, CEMENT_SIZE, CEMENT_SIZE);
            g2.dispose();

            result.add(brickWithCement);
        }
        return result;
    }

    @Override
    public BufferedImage getBackground(int wBackground, int hBackground) {
        BufferedImage resultImage = new BufferedImage(wBackground, hBackground, BufferedImage.TYPE_INT_ARGB);

        List<BufferedImage> bricks = parseBricksImage();
        int brickWidth = bricks.get(0).getWidth();
        int brickHeight = bricks.get(0).getHeight();

        Graphics2D g2 = resultImage.createGraphics();
        for (int x = 0; x < wBackground + brickWidth; x += brickWidth) {
            for (int y = 0; y < hBackground; y += brickHeight) {
                g2.drawImage(bricks.get(new Random().nextInt(bricks.size())), null,
                        x - ((y / brickHeight) % 2) * (brickWidth / 2), y);
            }
        }
        g2.dispose();
        return resultImage;
    }

    public static void main(String[] args) {
        Background background = new RedBrickBackground();
        int[] size = BackgroundImageTest.getSize();
        BackgroundImageTest.backgroundTest(background.getBackground(size[0], size[1]));
    }
}
