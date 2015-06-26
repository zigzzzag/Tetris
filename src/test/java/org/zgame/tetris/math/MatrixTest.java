package org.zgame.tetris.math;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.matr.SubMatr;

import java.util.Arrays;

/**
 * Created by mnikiforov on 07.06.2015.
 */
public class MatrixTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(MatrixTest.class);

    public void testTransposeMatrixCounterClockWise() {
        byte[][] matr = new byte[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        byte[][] matrExpected = new byte[][]{
                {3, 6, 9},
                {2, 5, 8},
                {1, 4, 7}
        };

        SubMatr subMatr = new SubMatr(3, 3).rowCoord(0).columnCoord(0);
        subMatr.setMatr(matr);
        subMatr.transposeMatrCounterClockWise();

        if (Arrays.deepEquals(subMatr.getMatr(), matrExpected)) {
            log.debug("testTransposeMatrixCounterClockWise is passed!");
            assertTrue(true);
        } else {
            log.error("testTransposeMatrixCounterClockWise is not passed");
            log.debug("transponseMatr: {}", Arrays.deepToString(subMatr.getMatr()));
            log.debug("matrExpected:   {}", Arrays.deepToString(matrExpected));
            assertTrue(false);
        }
    }

    public void testTransposeMatrixClockWise() {
        byte[][] matr = new byte[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        byte[][] matrExpected = new byte[][]{
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };

        SubMatr subMatr = new SubMatr(3, 3).rowCoord(0).columnCoord(0);
        subMatr.setMatr(matr);
        subMatr.transposeMatrClockWise();

        if (Arrays.deepEquals(subMatr.getMatr(), matrExpected)) {
            log.debug("testTransposeMatrixClockWise is passed!");
            assertTrue(true);
        } else {
            log.error("testTransposeMatrixClockWise is not passed");
            log.debug("transponseMatr: {}", Arrays.deepToString(subMatr.getMatr()));
            log.debug("matrExpected:   {}", Arrays.deepToString(matrExpected));
            assertTrue(false);
        }
    }
}
