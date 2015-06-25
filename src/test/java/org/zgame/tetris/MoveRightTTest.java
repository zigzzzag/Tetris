package org.zgame.tetris;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.FigureType;
import org.zgame.tetris.component.RootGlass;
import org.zgame.tetris.component.TemplateOfFigure;

import java.util.Arrays;

/**
 * Created by mnikiforov on 07.06.2015.
 */
public class MoveRightTTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(MoveRightTTest.class);

    public void testCaseFigure_3_0() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3  4  5  6   7   8  9
            /*0*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*1*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*2*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*3*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*4*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*5*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*6*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*7*/{0, 0, 0, 0, 0, 0, 0, 00,  0, 0},
            /*8*/{0, 0, 0, 0, 0, 0, 0, 00, 00, 0},
            /*9*/{0, 0, 0, 0, 0, 0, 0, 00,  0, 0},
           /*10*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*11*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*12*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*13*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*14*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*15*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*16*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*17*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*18*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*19*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0}
        };
        byte[][] matrExpected = new byte[][]{
            //    0  1  2  3  4  5  6  7   8   9
            /*0*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*1*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*2*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*3*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*4*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*5*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*6*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*7*/{0, 0, 0, 0, 0, 0, 0, 0, 01,  0},
            /*8*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 01},
            /*9*/{0, 0, 0, 0, 0, 0, 0, 0, 01,  0},
           /*10*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*11*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*12*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*13*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*14*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*15*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*16*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*17*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*18*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*19*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(FigureType.T, 7, 7).rotationAngleInt(270);

        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure().getMatr(), matrExpected)) {
            log.debug("testCaseFigure_3_0 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_0 is not passed");
            log.debug("tof.getFigure().getMatr(): {}", Arrays.deepToString(tof.getFigure().getMatr()));
            log.debug("matrExpected):   {}", Arrays.deepToString(matrExpected));
            assertTrue(false);
        }
    }
}
