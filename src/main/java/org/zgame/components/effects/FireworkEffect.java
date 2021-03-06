package org.zgame.components.effects;

import java.util.Random;
import org.zgame.components.particles.FireworkParticle;
import org.zgame.tetris.component.matr.Matr;
import org.zgame.utils.Constants;

/**
 * Created by sbt-nikiforov-mo on 21.01.16.
 */
public class FireworkEffect extends Effect {

    public FireworkEffect(int particleCount, int row) {
        this.particles = new FireworkParticle[particleCount];
        init(row, 0, row, Constants.MATR_COLUMN - 1);
    }

    public FireworkEffect(int particleCount) {
        this(particleCount, 0);
    }

    @Override
    protected void init(int row1, int column1, int row2, int column2) {
        for (int i = 0; i < particles.length; i++) {
            this.particles[i] = new FireworkParticle(
                    Matr.convertFromIndexColumn(column1 + new Random().nextInt(column2 - column1 + 1)),
                    Matr.convertFromIndexRow(row1 + new Random().nextInt(row2 - row1 + 1))
            );
        }
    }
}