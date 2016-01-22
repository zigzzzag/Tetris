package org.zgame.components.particles;

import java.awt.Graphics2D;
import org.zgame.utils.Constants;

/**
 * Created by sbt-nikiforov-mo on 21.01.16.
 */
public abstract class Particle {

    protected int particleCount = Constants.PARTICLE_COUNT_DEFAULT;
    protected double maxSpeed = Constants.PARTICLE_MAX_SPEED_DEFAULT;

    public abstract void reset();

    public abstract void render(Graphics2D g);

    public abstract void tick();


    public int getParticleCount() {
        return particleCount;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
