package org.zgame.tetris.component;

import org.zgame.stage.TetrisStage;
import org.zgame.utils.Constants;

/**
 * Created by SBT-Nikiforov-MO on 28.05.2015.
 */
public class RootGlass {

    private byte[][] filledGlass = new byte[Constants.matrY][Constants.matrX];

    public RootGlass() {
    }

    public void deleteFullLine(int lineNumber) {
        for (int i = lineNumber - 1; i >= 0; i--) {
            for (int j = 0; j < Constants.matrX; j++) {
                filledGlass[i + 1][j] = filledGlass[i][j];
                filledGlass[i][j] = 0;
            }
        }
    }

    public Boolean verifyGameOver() {
        boolean over = false;
        for (int j = 2; j < Constants.matrX - 2; j++) {
            if (filledGlass[0][j] != 0) {
                over = true;
            }
        }

        return false;
    }

    public byte[][] getFilledGlass() {
        return filledGlass;
    }

    public void setFilledGlass(byte[][] filledGlass) {
        this.filledGlass = filledGlass;
    }
}
