package org.zgame.components.background;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mnikiforov on 21.07.2015.
 */
public class BackgroundImageTest {

    public static int[] getSize() {GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultGraphicsDevice = gEnvironment.getDefaultScreenDevice();
        int widthScreen = defaultGraphicsDevice.getDisplayMode().getWidth();
        int heightScreen = defaultGraphicsDevice.getDisplayMode().getHeight();

        int[] result = new int[2];
        result[0] = widthScreen;
        result[1] = heightScreen;
        return result;
    }

    public static void backgroundTest(BufferedImage backgroundImage) {
        JFrame testFrame = new JFrame("Test Background");
        testFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImagePane imagePane = new ImagePane(backgroundImage);
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
