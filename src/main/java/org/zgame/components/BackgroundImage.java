package org.zgame.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mnikiforov on 21.07.2015.
 */
public class BackgroundImage {

    public static void main(String[] args) {
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultGraphicsDevice = gEnvironment.getDefaultScreenDevice();
        int width = defaultGraphicsDevice.getDisplayMode().getWidth();
        int height = defaultGraphicsDevice.getDisplayMode().getHeight();
        System.out.println(width + " " + height);

        BufferedImage testImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = testImage.createGraphics();

        JFrame testFrame = new JFrame("Test Background");
        testFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        testFrame.setVisible(true);

    }
}
