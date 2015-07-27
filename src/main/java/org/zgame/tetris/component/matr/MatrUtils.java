package org.zgame.tetris.component.matr;

/**
 * Created by SBT-Nikiforov-MO on 17.07.2015.
 */
public class MatrUtils {

    public static void copyMatr(byte[][] sourceMatr, byte[][] copiedMatr) {
        for (int row = 0; row < sourceMatr.length; row++) {
            for (int column = 0; column < sourceMatr[0].length; column++) {
                copiedMatr[row][column] = sourceMatr[row][column];
            }
        }
    }

    public static byte[][] getDownMatr(byte[][] sourceMatr) {
        byte[][] downMatr = new byte[sourceMatr.length][sourceMatr[0].length];

        copyMatr(sourceMatr, downMatr);

        for (int row = 0; row < sourceMatr.length; row++) {
            for (int column = 0; column < sourceMatr[0].length; column++) {
                if (sourceMatr[row][column] != 0) {
                    downMatr[row + 1][column] = sourceMatr[row][column];
                }
            }
        }

        return downMatr;
    }

    public static byte[][] getUpMatr(byte[][] sourceMatr) {
        byte[][] upMatr = new byte[sourceMatr.length][sourceMatr[0].length];

        copyMatr(sourceMatr, upMatr);

        for (int row = 0; row < sourceMatr.length; row++) {
            for (int column = 0; column < sourceMatr[0].length; column++) {
                if (sourceMatr[row][column] != 0) {
                    upMatr[row - 1][column] = sourceMatr[row][column];
                }
            }
        }

        return upMatr;
    }

    public static byte[][] getLeftMatr(byte[][] sourceMatr) {
        byte[][] leftMatr = new byte[sourceMatr.length][sourceMatr[0].length];

        copyMatr(sourceMatr, leftMatr);

        for (int row = 0; row < sourceMatr.length; row++) {
            for (int column = 0; column < sourceMatr[0].length; column++) {
                if (sourceMatr[row][column] != 0) {
                    leftMatr[row][column - 1] = sourceMatr[row][column];
                }
            }
        }

        return leftMatr;
    }

    public static byte[][] getRightMatr(byte[][] sourceMatr) {
        byte[][] leftMatr = new byte[sourceMatr.length][sourceMatr[0].length];

        copyMatr(sourceMatr, leftMatr);

        for (int row = 0; row < sourceMatr.length; row++) {
            for (int column = 0; column < sourceMatr[0].length; column++) {
                if (sourceMatr[row][column] != 0) {
                    leftMatr[row][column + 1] = sourceMatr[row][column];
                }
            }
        }

        return leftMatr;
    }
}
