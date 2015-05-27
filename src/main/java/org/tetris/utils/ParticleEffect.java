/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.utils;

import java.awt.*;

/**
 * @author user
 */
public class ParticleEffect {

    private static final int PARTICLES = 25;
    public static final int TIME = 100;
    private static final double MAX_SPEED = 10;
    private int blink = 0;

    class Line {

        protected double x1;
        protected double y1;
        protected double x2;
        protected double y2;
        protected double u;
        protected double v;
        protected int ticks;
        protected int r;
        protected int g;
        protected int b;

        public Line(int x1, int y1, double u, double v) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1;
            this.y2 = y1;
            this.u = u;
            this.v = v;
            this.r = (int) (Math.random() * 55) + 200;
            this.g = (int) (Math.random() * 55) + 200;
            this.b = (int) (Math.random() * 55) + 200;
            ticks = 0;
            blink = (int) (Math.random() * TIME / 3);
        }

        public void tick() {
            x2 = x1;
            y2 = y1;
            x1 += u;
            y1 += v;
            ticks += 1;
            if (v < MAX_SPEED) {
                v += 0.2;
            }
//            v += (Math.random() - 0.5d) / 10d;
//            u += (Math.random() - 0.5d) / 10d;
            u = u * 0.99;
            v = v * 0.99;
            blink -= 1;
            if (blink < 0) {
                blink = TIME / 3;
            }
            //}
        }
    }

    private Line[] particles = new Line[PARTICLES];

    public ParticleEffect(int vx, int vy) {
        for (int i = 0; i < PARTICLES; i++) {
            particles[i] = new Line(vx + (int) (Math.random() * 15d), vy + (int) (Math.random() * 15d), MAX_SPEED - (Math.random() * (MAX_SPEED * 2d)), -Math.random() * MAX_SPEED);
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < PARTICLES; i++) {
            particles[i].tick();
            int alpha = 255 - (255 / TIME) * particles[i].ticks;

            //g.drawLine((int) particles[i].x1, (int) particles[i].y1, (int) particles[i].x2, (int) particles[i].y2);
            //g.setColor(new Color(255, 255, 255, alpha < 0 ? 0 : alpha));
            double v = particles[i].x1 - particles[i].x2;
            double u = particles[i].y1 - particles[i].y2;
            double b = Math.sqrt(u * u + v * v) * 100;
            if (blink < 3) {
                g.setColor(new Color(particles[i].r, particles[i].g, particles[i].b, alpha < 0 ? 0 : alpha));
                g.fillOval((int) particles[i].x1, (int) particles[i].y1, 2, 2);
            } else {
                g.setColor(new Color(0, 0, 128, alpha < 0 ? 0 : alpha));
                g.fillOval((int) particles[i].x1, (int) particles[i].y1, 2, 2);
            }
        }
    }
}
