/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.utils.FPSData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.VolatileImage;

/**
 * @author user
 */
public class Screen extends JFrame implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(Screen.class);
    private GraphicsDevice defaultGraphicsDevice;
    private StageInterface currentStage;
    private VolatileImage image;
    private FPSData fpsData;

    public Screen() {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fpsData = new FPSData();
    }

    public void initScreen(StageInterface cg) {
        this.currentStage = cg;
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        defaultGraphicsDevice = gEnvironment.getDefaultScreenDevice();

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setIgnoreRepaint(true);
        setVisible(true);

        createBufferStrategy(2);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentStage != null) {
                    currentStage.processEvent(new ScreenClickEvent(e.getXOnScreen(), e.getYOnScreen()));
                }
            }
        });

        this.image = defaultGraphicsDevice.getDefaultConfiguration().createCompatibleVolatileImage(
                defaultGraphicsDevice.getDisplayMode().getWidth(),
                defaultGraphicsDevice.getDisplayMode().getHeight());
    }

    public void updateLoop() {
        // For max accuracy, resetting the time since last update so
        // animations and sprite positions remain in their standard first
        // position
        fpsData.resetTimeOfLastUpdate();
        // Just loop and loop forever, update state and then draw.
        while (true) {
            long nanoTimeAtStartOfUpdate = System.nanoTime();

            fpsData.updateData();
            try {
                Graphics2D g = image.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
                g.clearRect(0, 0, getWidth(), getHeight());
                if (currentStage != null) {
                    currentStage.render(g);
                }
                g.dispose();

                Graphics graphics = getBufferStrategy().getDrawGraphics();
                graphics.drawImage(image, 0, 0, null);
                graphics.dispose();
                if (!getBufferStrategy().contentsLost()) {
                    getBufferStrategy().show();
                } else {
                    Toolkit.getDefaultToolkit().sync();
                }
            }
            // This catch is to allow the application to not stop
            // working when the application encounters the possible bug:
            // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6933331
            // One work around to not encounter this is to Disable d3d
            // using -Dsun.java2d.d3d=false
            // Not sure why the bug is said to "... has no consequences
            // other than a stack trace dump in a console (no hang... ",
            // as people are generally not going to catch an
            // IllegalStateException...
            // You can try to see if you can get the exception to print
            // by resizing the window rapidly on the primary or secondary,
            // or dragging the window off and on the primary monitor.
            // This of course assumes you are using d3d
            catch (IllegalStateException e) {
                e.printStackTrace();
            }

            fpsData.waitUntilNextUpdate(nanoTimeAtStartOfUpdate);
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {
        log.info("keyTyped {}", e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        log.info("keyPressed {}", e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        log.info("keyReleased {}", e);
    }

    public void setCurrentStage(StageInterface currentStage) {
        this.currentStage = currentStage;
    }

    public FPSData getFpsData() {
        return fpsData;
    }
}
