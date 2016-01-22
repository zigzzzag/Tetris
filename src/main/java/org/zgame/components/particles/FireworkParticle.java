/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.components.particles;

import java.awt.Color;
import java.awt.Graphics2D;
import org.zgame.utils.Constants;

/**
 * @author user
 */
public class FireworkParticle extends Particle {

    private Line[] lines;

    public FireworkParticle(int x, int y) {
        this(Constants.PARTICLE_COUNT_DEFAULT, Constants.PARTICLE_MAX_SPEED_DEFAULT, x, y);
    }

    public FireworkParticle(int linesCount, double maxSpeed, int x, int y) {
        this.particleCount = linesCount;
        this.maxSpeed = maxSpeed;
        this.lines = new Line[linesCount];

        for (int i = 0; i < linesCount; i++) {
            this.lines[i] = new Line(
                    x + (int) (Math.random() * Constants.QUADRATE_SIZE),
                    y + (int) (Math.random() * Constants.QUADRATE_SIZE),
                    getVXRandom(),
                    getVYRandom());
        }
    }

    private double getVXRandom() {
        return maxSpeed - (Math.random() * (maxSpeed * 2d));
    }

    private double getVYRandom() {
        return -Math.random() * maxSpeed;
    }

    @Override
    public void reset() {
        for (Line line : lines) {
            line.reset();
            line.setV_x(getVXRandom());
            line.setV_y(getVYRandom());
            line.setTicks(0);
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (Line line : lines) {
            int alpha = 255;//255 - (255 / TIME) * line.ticks;

            g.setColor(new Color(line.r, line.g, line.b, alpha < 0 ? 0 : alpha));
            g.fillOval((int) line.x1, (int) line.y1, 2, 2);
        }
    }

    @Override
    public void tick() {
        for (Line line : lines) {
            line.tick();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Line line : lines) {
            sb.append(line.toString()).append("\r\n");
        }
        return sb.toString();
    }
}
