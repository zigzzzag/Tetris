package org.zgame.components.effects;

import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.components.particles.Particle;
import org.zgame.utils.Constants;

/**
 * Created by sbt-nikiforov-mo on 21.01.16.
 */
public abstract class Effect {

    private static final Logger log = LoggerFactory.getLogger(Effect.class);
    protected Particle[] particles;
    private Set<Particle> allParticles = new CopyOnWriteArraySet<>();
    protected long timeToShow = Constants.PARTICLE_TIME_TO_SHOW_DEFAULT;//in millis
    protected static final ExecutorService executorService = Executors.newCachedThreadPool();

    protected abstract void init(int row1, int column1, int row2, int column2);

    protected void reset(int row1, int column1, int row2, int column2) {
        init(row1, column1, row2, column2);
    }

    public void startEffect(int row1, int column1, int row2, int column2) {
        reset(row1, column1, row2, column2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Particle[] renderParticles = new Particle[particles.length];
                System.arraycopy(particles, 0, renderParticles, 0, particles.length);
                Arrays.fill(particles, null);
                for (Particle p : renderParticles) {
                    allParticles.add(p);
                }

                try {
                    long timeStart = System.currentTimeMillis();
                    while (System.currentTimeMillis() - timeStart < timeToShow) {
                        for (Particle p : renderParticles) {
                            p.tick();
                        }

                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                } finally {
                    for (Particle p : renderParticles) {
                        allParticles.remove(p);
                    }
                }
            }
        });
    }

    public void render(Graphics2D g2d) {
        Iterator<Particle> it = allParticles.iterator();
        while (it.hasNext()) {
            it.next().render(g2d);
        }
    }
}
