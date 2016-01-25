package org.zgame.components.particles;

import java.awt.Graphics2D;
import org.zgame.utils.Constants;

/**
 * Created by sbt-nikiforov-mo on 21.01.16.
 */
public abstract class Particle {

    protected double maxSpeed = Constants.PARTICLE_MAX_SPEED_DEFAULT;

    protected double xStart;
    protected double yStart;
    protected double x;
    protected double y;
    protected double v_x;
    protected double v_y;
    protected int ticks;
    protected int r;
    protected int g;
    protected int b;

    protected abstract void init(double x, double y);

    public abstract void render(Graphics2D g);

    public abstract void tick();

    protected abstract double getVXRandom();

    protected abstract double getVYRandom();
}
