package org.zgame.components.effects;

import java.awt.Graphics2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.zgame.components.particles.Particle;
import org.zgame.utils.Constants;

/**
 * Created by sbt-nikiforov-mo on 21.01.16.
 */
public abstract class Effect {

    protected Particle[] particles;
    //in millis
    protected long timeToShow = Constants.PARTICLE_TIME_TO_SHOW_DEFAULT;
    protected static final ExecutorService executorService = Executors.newCachedThreadPool();

    public abstract void reset(int row);

    public abstract void startEffect(int row);

    public abstract void render(Graphics2D g2d);

    public Particle[] getParticles() {
        return particles;
    }
}
