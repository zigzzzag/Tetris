package org.zgame.components.effects;

import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.components.particles.FireworkParticle;
import org.zgame.components.particles.Particle;
import org.zgame.tetris.component.matr.Matr;

/**
 * Created by sbt-nikiforov-mo on 21.01.16.
 */
public class FireworkEffect extends Effect {

    private static final Logger log = LoggerFactory.getLogger(FireworkEffect.class);
    private Set<Particle> allParticles = new CopyOnWriteArraySet<>();

    public FireworkEffect(int particleCount) {
        this.particles = new FireworkParticle[particleCount];

        for (int i = 0; i < particles.length; i++) {
            this.particles[i] = new FireworkParticle(Matr.converFromIndexColumn(i), Matr.converFromIndexColumn(i));
        }
    }

    @Override
    public void reset(int row) {
        for (int i = 0; i < particles.length; i++) {
            this.particles[i] = new FireworkParticle(Matr.converFromIndexColumn(i), Matr.converFromIndexRow(row));
        }
    }

    @Override
    public void startEffect(int row) {
        reset(row);
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

    @Override
    public void render(Graphics2D g2d) {
        for (Particle p : allParticles) {
            p.render(g2d);
        }
    }
}
