package org.zgame.tetris.component.matr;

import org.zgame.tetris.component.RootGlass;

/**
 * Created by SBT-Nikiforov-MO on 28.07.2015.
 */
public class ShadowMatr extends Matr {

    public ShadowMatr(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public boolean isDownAvailable(RootGlass rootGlass) {
        int maxRow = getMaxRow();
        if (maxRow < rootGlass.getRowCount() - 1) {
            byte[][] matrDown = MatrUtils.getDownMatr(matr);
            if (!rootGlass.hasIntersectionWithMatr(matrDown)) {
                return true;
            }
        }
        return false;
    }

    public ShadowMatr clone() {
        ShadowMatr matrClone = new ShadowMatr(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                matrClone.setElement(getElement(row, column), row, column);
            }
        }
        return matrClone;
    }
}
