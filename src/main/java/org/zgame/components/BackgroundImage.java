package org.zgame.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by mnikiforov on 21.07.2015.
 */
public class BackgroundImage {

    public static BufferedImage getBackgroundImage(int widthScreen, int heightScreen) {
        BufferedImage resultImage = new BufferedImage(widthScreen, heightScreen, BufferedImage.TYPE_INT_ARGB);

        int sizeQuad = 256;
        Graphics2D g2 = resultImage.createGraphics();
        for (int row = 0; row < widthScreen; row += sizeQuad) {
            for (int column = 0; column < heightScreen; column += sizeQuad) {
                g2.setColor(new Color(50 + new Random().nextInt(100), 160, 50 + 50 + new Random().nextInt(100)));
                g2.fillRect(row, column, sizeQuad, sizeQuad);
            }
        }
        return resultImage;
    }

    public static void main(String[] args) {
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultGraphicsDevice = gEnvironment.getDefaultScreenDevice();
        int widthScreen = defaultGraphicsDevice.getDisplayMode().getWidth();
        int heightScreen = defaultGraphicsDevice.getDisplayMode().getHeight();
        System.out.println(widthScreen + " " + heightScreen);

        BufferedImage testImage = new BufferedImage(widthScreen, heightScreen, BufferedImage.TYPE_INT_ARGB);

        int sizeQuad = 256;
        Graphics2D g2 = testImage.createGraphics();
        for (int row = 0; row < widthScreen; row += sizeQuad) {
            for (int column = 0; column < heightScreen; column += sizeQuad) {
                g2.setColor(new Color(50 + new Random().nextInt(100), 160, 50 + 50 + new Random().nextInt(100)));
                g2.fillRect(row, column, sizeQuad, sizeQuad);
            }
        }

        JFrame testFrame = new JFrame("Test Background");
        testFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImagePane imagePane = new ImagePane(testImage);
        testFrame.add(imagePane);
    }

    private static class ImagePane extends JPanel {

        private BufferedImage background;

        public ImagePane(BufferedImage bufferedImage) {
            try {
                this.background = bufferedImage;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return background == null ? super.getPreferredSize() : new Dimension(background.getWidth(), background.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                int x = (getWidth() - background.getWidth()) / 2;
                int y = (getHeight() - background.getHeight()) / 2;
                g.drawImage(background, x, y, this);
            }
        }
    }
}
