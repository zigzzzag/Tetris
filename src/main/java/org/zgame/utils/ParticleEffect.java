/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.utils;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author user
 */
public class ParticleEffect {

    private static final int PARTICLES_COUNT = 25;
    public static final int TIME = 100;
    private static final double MAX_SPEED = 6;

    class Line {

        protected double x1;
        protected double y1;
        protected double x2;
        protected double y2;
        protected double v_x;
        protected double v_y;
        protected int ticks;
        protected int r;
        protected int g;
        protected int b;

        public Line(int x1, int y1, double v_x, double v_y) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1;
            this.y2 = y1;
            this.v_x = v_x;
            this.v_y = v_y;
            this.r = (int) (Math.random() * 256);
            this.g = (int) (Math.random() * 256);
            this.b = (int) (Math.random() * 256);
            ticks = 0;
        }

        public void tick() {
            x2 = x1;
            y2 = y1;
            x1 += v_x;
            y1 += v_y;

            ticks += 1;

            v_x = v_x * 0.99;

            v_y += 0.2;
            v_y = v_y * 0.99;
        }
    }

    private Line[] particles = new Line[PARTICLES_COUNT];

    public ParticleEffect(int vx, int vy) {
        for (int i = 0; i < PARTICLES_COUNT; i++) {
            particles[i] = new Line(
                    vx + (int) (Math.random() * Constants.QUADRATE_SIZE),
                    vy + (int) (Math.random() * Constants.QUADRATE_SIZE),
                    MAX_SPEED - (Math.random() * (MAX_SPEED * 2d)),
                    -Math.random() * MAX_SPEED);
        }
    }

    public void render(Graphics2D g) {
        for (Line line : particles) {
            line.tick();
            int alpha = 255;//255 - (255 / TIME) * line.ticks;

            g.setColor(new Color(line.r, line.g, line.b, alpha < 0 ? 0 : alpha));
            g.fillOval((int) line.x1, (int) line.y1, 2, 2);
        }
    }
}
