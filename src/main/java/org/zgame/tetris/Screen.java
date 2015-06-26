/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;
import java.lang.reflect.InvocationTargetException;

/**
 * @author user
 */
public class Screen extends JFrame implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(Screen.class);
    private GraphicsDevice gd = null;
    private StageInterface currentStage = null;
    private BufferStrategy bufferStrategy;

    public Screen() {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initScreen() {
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = gEnvironment.getDefaultScreenDevice();
        setUndecorated(true);

        gd.setFullScreenWindow(this);

        setDisplayMode(1024, 768, 32);

        setBufferStrategy();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentStage != null) {
                    currentStage.processEvent(new ScreenClickEvent(e.getXOnScreen(), e.getYOnScreen()));
                }
            }
        });
    }

    public void setDisplayMode(int width, int height, int bpp) {
        DisplayMode dm = new DisplayMode(width, height, bpp, DisplayMode.REFRESH_RATE_UNKNOWN);
        gd.setDisplayMode(dm);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void setCurrentStage(StageInterface currentStage) {
        this.currentStage = currentStage;
    }

    private VolatileImage image = null;

    public void update() {
        if (image == null) {
            image = gd.getDefaultConfiguration().createCompatibleVolatileImage(1024, 768, 1);
            image.setAccelerationPriority(1);
        }

        Graphics2D g = (Graphics2D) image.getGraphics();

//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g.clearRect(0, 0, getWidth(), getHeight());
        if (currentStage != null) {
            currentStage.render(g);
        }

        Graphics2D gScr = (Graphics2D) bufferStrategy.getDrawGraphics();
        gScr.drawImage(image, 0, 0, null);
        gScr.dispose();

        if (!bufferStrategy.contentsLost()) {
            bufferStrategy.show();
        } else {
            Toolkit.getDefaultToolkit().sync();
        }
    }

    private void setBufferStrategy() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    createBufferStrategy(3);
                }
            });
        } catch (InterruptedException e) {
            log.error("Error while creating buffer strategy", e);
            System.exit(0);
        } catch (InvocationTargetException e) {
            log.error("Error while creating buffer strategy", e);
            System.exit(0);
        }
        try { // sleep to give time for buffer strategy to be done
            Thread.sleep(500); // 0.5 sec
        } catch (InterruptedException ex) {
            log.error(ex.getMessage(), ex);
        }
        bufferStrategy = getBufferStrategy();
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
}
