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

    private double x1;
    private double y1;

    public FireworkParticle(int x, int y) {
        this(Constants.PARTICLE_MAX_SPEED_DEFAULT, x, y);
    }

    public FireworkParticle(double maxSpeed, int x, int y) {
        this.maxSpeed = maxSpeed;

        init(x, y);
    }

    @Override
    protected double getVXRandom() {
        return maxSpeed - (Math.random() * (maxSpeed * 2d));
    }

    @Override
    protected double getVYRandom() {
        return -Math.random() * maxSpeed;
    }

    @Override
    protected void init(double x, double y) {
        this.xStart = x;
        this.yStart = y;

        this.x = x;
        this.y = y;
        this.x1 = x;
        this.y1 = y;

        this.v_x = getVXRandom();
        this.v_y = getVYRandom();
        this.r = (int) (Math.random() * 256);
        this.g = (int) (Math.random() * 256);
        this.b = (int) (Math.random() * 256);
        this.ticks = 0;
    }

    @Override
    public void render(Graphics2D g2d) {
        int alpha = 255;//255 - (255 / TIME) * line.ticks;

        g2d.setColor(new Color(r, g, b, alpha < 0 ? 0 : alpha));
//        g2d.fillOval((int) x, (int) y, 2, 2);
        g2d.drawLine((int) x, (int) y, (int) x1, (int) y1);
    }

    @Override
    public void tick() {
        x1 = x;
        y1 = y;
        x += v_x;
        y += v_y;

        ticks += 1;

        v_x = v_x * 0.99;

        v_y += 0.2;
        v_y = v_y * 0.99;
    }

    private static final String PF = "%.2f";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LINE_").append(String.format("%-10d", hashCode())).append("[")
                .append("x=").append(String.format(PF, x)).append(",")
                .append("x1=").append(String.format(PF, x1)).append(",")
                .append("y=").append(String.format(PF, y1)).append(",")
                .append("y1=").append(String.format(PF, y1)).append(",")
                .append("xStart=").append(String.format(PF, xStart)).append(",")
                .append("yStart=").append(String.format(PF, yStart)).append(",")
                .append("ticks=").append(ticks).append(",")
                .append("v_x=").append(String.format(PF, v_x)).append(",")
                .append("v_y=").append(String.format(PF, v_y))
                .append("]");
        return sb.toString();
    }
}
