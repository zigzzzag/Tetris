package org.zgame.tetris;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.FigureType;
import org.zgame.tetris.component.RootGlass;
import org.zgame.tetris.component.TemplateOfFigure;
import org.zgame.utils.Constants;

import java.util.Arrays;

/**
 * Created by SBT-Nikiforov-MO on 26.06.2015.
 */
public class RotateStickTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(RotateStickTest.class);

    public void testStickRotate_01() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1   2   3   4   5  6  7  8  9
            /*0*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*1*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*2*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*3*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 00, 00, 00, 00, 0, 0, 0, 0},
            /*6*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*10*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*11*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*12*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*14*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0,  0,  0,  0,  0, 0, 0, 0, 0}
        };

        byte[][] matrExpected = new byte[][]{
            //    0  1   2  3  4  5  6  7  8  9
            /*0*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*1*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*2*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*3*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*4*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*5*/{0, 0, 01, 0, 0, 0, 0, 0, 0, 0},
            /*6*/{0, 0, 01, 0, 0, 0, 0, 0, 0, 0},
            /*7*/{0, 0, 01, 0, 0, 0, 0, 0, 0, 0},
            /*8*/{0, 0, 01, 0, 0, 0, 0, 0, 0, 0},
            /*9*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*10*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*11*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*12*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*13*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*14*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*15*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*16*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*17*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*18*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*19*/{0, 0,  0, 0, 0, 0, 0, 0, 0, 0}
        };
        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(FigureType.STICK, 5, 2);

        tof.rotate(rootGlass);

        if (Arrays.deepEquals(tof.getFigure().getMatr(), matrExpected)) {
            log.debug("testStickRotate_01 is passed!");
            assertTrue(true);
        } else {
            log.error("testStickRotate_01 is not passed");
            log.debug("tof.getFigure().getMatr(): {}", Arrays.deepToString(tof.getFigure().getMatr()));
            log.debug("matrExpected:              {}", Arrays.deepToString(matrExpected));
            assertTrue(false);
        }
    }

    public void testStickRotate_02() {
        byte[][] tofMatrBefore = new byte[][]{
            //    0  1  2  3  4  5  6  7   8  9
            /*0*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*1*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*2*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*3*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*4*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*5*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*6*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*7*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*8*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*9*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*10*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*11*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*12*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*13*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*14*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*15*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*16*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*17*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*18*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*19*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0}
        };

        byte[][] matrExpected = new byte[][]{
            //    0  1  2  3  4  5   6   7   8   9
            /*0*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*1*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*2*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*3*/{0, 0, 0, 0, 0, 0, 01, 01, 01, 01},
            /*4*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*5*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*6*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*7*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*8*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
            /*9*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*10*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*11*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*12*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*13*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*14*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*15*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*16*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*17*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*18*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0},
           /*19*/{0, 0, 0, 0, 0, 0,  0,  0,  0,  0}
        };
        RootGlass rootGlass = new RootGlass(Constants.EMPTY_ROOT_GLASS_MATR);
        TemplateOfFigure tof = new TemplateOfFigure(FigureType.STICK, 3, 6).rotationAngleInt(90);
        tof.moveRight(rootGlass);
        tof.moveRight(rootGlass);

        if (!Arrays.deepEquals(tof.getFigure().getMatr(), tofMatrBefore)) {
            log.error("testStickRotate_02 is not passed");
            log.debug("tof.getFigure().getMatr(): {}", Arrays.deepToString(tof.getFigure().getMatr()));
            log.debug("tofMatrBefore:             {}", Arrays.deepToString(tofMatrBefore));
            assertTrue(false);
        }

        tof.rotate(rootGlass);

        if (Arrays.deepEquals(tof.getFigure().getMatr(), matrExpected)) {
            log.debug("testStickRotate_02 is passed!");
            assertTrue(true);
        } else {
            log.error("testStickRotate_02 is not passed");
            log.debug("tof.getFigure().getMatr(): {}", Arrays.deepToString(tof.getFigure().getMatr()));
            log.debug("matrExpected:              {}", Arrays.deepToString(matrExpected));
            assertTrue(false);
        }
    }

    public void testStickRotate_03() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3  4  5   6  7   8  9
            /*0*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
            /*1*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
            /*2*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
            /*3*/{0, 0, 0, 0, 0, 0, 01, 0, 00, 0},
            /*4*/{0, 0, 0, 0, 0, 0, 01, 0, 00, 0},
            /*5*/{0, 0, 0, 0, 0, 0, 01, 0, 00, 0},
            /*6*/{0, 0, 0, 0, 0, 0, 01, 0, 00, 0},
            /*7*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
            /*8*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
            /*9*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*10*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*11*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*12*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*13*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*14*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*15*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*16*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*17*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*18*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0},
           /*19*/{0, 0, 0, 0, 0, 0,  0, 0,  0, 0}
        };

        byte[][] tofMatrBefore = new byte[][]{
            //    0  1  2  3  4  5  6  7   8  9
            /*0*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*1*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*2*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*3*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*4*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*5*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*6*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*7*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*8*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*9*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*10*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*11*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*12*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*13*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*14*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*15*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*16*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*17*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*18*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*19*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0}
        };

        byte[][] matrExpected = new byte[][]{
            //    0  1  2  3  4  5  6  7   8  9
            /*0*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*1*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*2*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*3*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*4*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*5*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*6*/{0, 0, 0, 0, 0, 0, 0, 0, 01, 0},
            /*7*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*8*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
            /*9*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*10*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*11*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*12*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*13*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*14*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*15*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*16*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*17*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*18*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0},
           /*19*/{0, 0, 0, 0, 0, 0, 0, 0,  0, 0}
        };
        RootGlass rootGlassBefore = new RootGlass(Constants.EMPTY_ROOT_GLASS_MATR);
        TemplateOfFigure tof = new TemplateOfFigure(FigureType.STICK, 3, 6).rotationAngleInt(90);
        tof.moveRight(rootGlassBefore);
        tof.moveRight(rootGlassBefore);

        if (!Arrays.deepEquals(tof.getFigure().getMatr(), tofMatrBefore)) {
            log.error("testStickRotate_03 is not passed");
            log.debug("tof.getFigure().getMatr(): {}", Arrays.deepToString(tof.getFigure().getMatr()));
            log.debug("tofMatrBefore:             {}", Arrays.deepToString(tofMatrBefore));
            assertTrue(false);
        }

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        tof.rotate(rootGlass);

        if (Arrays.deepEquals(tof.getFigure().getMatr(), matrExpected)) {
            log.debug("testStickRotate_03 is passed!");
            assertTrue(true);
        } else {
            log.error("testStickRotate_03 is not passed");
            log.debug("tof.getFigure().getMatr(): {}", Arrays.deepToString(tof.getFigure().getMatr()));
            log.debug("matrExpected:              {}", Arrays.deepToString(matrExpected));
            assertTrue(false);
        }
    }
}
