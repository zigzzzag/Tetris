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
    private GraphicsDevice defaultGraphicsDevice = null;
    private StageInterface currentStage = null;

    public Screen() {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initScreen() {
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        defaultGraphicsDevice = gEnvironment.getDefaultScreenDevice();

        setUndecorated(true);
        defaultGraphicsDevice.setFullScreenWindow(this);

        this.createBufferStrategy(2);


//        setDisplayMode(1024, 768, 32);

//        setBufferStrategy();

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
        defaultGraphicsDevice.setDisplayMode(dm);
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
            image = defaultGraphicsDevice.getDefaultConfiguration().createCompatibleVolatileImage(1024, 768, 1);
            image.setAccelerationPriority(1);
        }

        Graphics2D g = (Graphics2D) image.getGraphics();

//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g.clearRect(0, 0, getWidth(), getHeight());
        if (currentStage != null) {
            currentStage.render(g);
        }

        Graphics2D gScr = (Graphics2D) getBufferStrategy().getDrawGraphics();
        gScr.drawImage(image, 0, 0, null);
        gScr.dispose();

        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        } else {
            Toolkit.getDefaultToolkit().sync();
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
}
