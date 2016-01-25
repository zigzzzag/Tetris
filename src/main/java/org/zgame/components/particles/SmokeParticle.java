package org.zgame.components.particles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import org.zgame.utils.Constants;

/**
 * Created by sbt-nikiforov-mo on 25.01.16.
 */
public class SmokeParticle extends Particle {

    private double radius = 5d;
    private double vradius = 1 + Math.random() * 0.05;
    private Color renderColor;
    private static Color[] smokeColors = new Color[]{
            new Color(200, 200, 200, 50),
            new Color(210, 210, 210, 60),
            new Color(220, 220, 220, 70),
            new Color(230, 230, 230, 80),
            new Color(240, 240, 240, 90)
    };

    public SmokeParticle(int x, int y) {
        this(Constants.PARTICLE_MAX_SPEED_DEFAULT, x, y);
    }

    public SmokeParticle(double maxSpeed, int x, int y) {
        this.maxSpeed = maxSpeed;
        this.renderColor = smokeColors[new Random().nextInt(smokeColors.length)];

        init(x, y);
    }

    @Override
    protected void init(double x, double y) {
        this.xStart = x;
        this.yStart = y;

        this.x = x;
        this.y = y;

        this.v_x = getVXRandom();
        this.v_y = getVYRandom();
        this.r = (int) (Math.random() * 256);
        this.g = (int) (Math.random() * 256);
        this.b = (int) (Math.random() * 256);
        this.ticks = 0;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(renderColor);
        g2d.fillOval((int) x, (int) y, (int) radius, (int) radius);
    }

    @Override
    public void tick() {
        x += v_x;
        y += v_y;

        ticks += 1;

        radius *= vradius;
        v_x *= 0.99;
        v_y *= 0.99;
    }

    @Override
    protected double getVXRandom() {
        return -maxSpeed + (Math.random() * (maxSpeed * 2d));
    }

    @Override
    protected double getVYRandom() {
        return -Math.random() * maxSpeed;
    }
}
