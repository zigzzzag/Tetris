package org.zgame.tetris;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.RootGlass;
import org.zgame.tetris.component.TemplateOfFigure;

import java.util.Arrays;

/**
 * Created by SBT-Nikiforov-MO on 28.05.2015.
 */
public class MoveRightGTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(MoveRightGTest.class);

    public void testCaseFigure_3_0() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3   4   5  6  7  8  9
            /*0*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 9, 5);

        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_0 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_0 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_1() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3   4   5  6  7  8  9
            /*0*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0, 00,  0, 1, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 1, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 9, 5);

        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_1 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_1 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_2() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3   4   5  6  7  8  9
            /*0*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0, 00,  1, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  1, 0, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 9, 4);

        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_2 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_2 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_3() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3   4   5  6  7  8  9
            /*0*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0, 00,  0, 1, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 1, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 9, 5);

        tof.moveRight(rootGlass);
        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_3 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_3 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_4() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3   4   5  6  7  8  9
            /*0*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0, 00,  0, 1, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 1, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 9, 5);

        for (int i = 0; i < 10; i++) {
            tof.moveRight(rootGlass);
        }

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_4 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_4 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_5() {
        byte[][] rootGlassMatr = new byte[][]{
                //    0  1  2  3  4  5  6   7   8  9
            /*0*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*1*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*2*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*3*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*4*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*5*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*6*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*7*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*8*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
            /*9*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*10*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*11*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*12*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*13*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*14*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*15*/{0, 0, 0, 0, 0, 0, 0, 00, 00, 0},
           /*16*/{0, 0, 0, 0, 0, 0, 0, 00,  0, 0},
           /*17*/{0, 0, 0, 0, 0, 0, 0, 00,  0, 0},
           /*18*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0},
           /*19*/{0, 0, 0, 0, 0, 0, 0,  0,  0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 15, 7);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 15, 8);

        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_5 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_5 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_6() {
        byte[][] rootGlassMatr = new byte[][]{
            //    0  1  2  3  4  5  6  7   8   9
            /*0*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*1*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*2*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*3*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*4*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*5*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*6*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*7*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*8*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
            /*9*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*10*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*11*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*12*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*13*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*14*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*15*/{0, 0, 0, 0, 0, 0, 0, 0, 00, 00},
           /*16*/{0, 0, 0, 0, 0, 0, 0, 0, 00,  0},
           /*17*/{0, 0, 0, 0, 0, 0, 0, 0, 00,  0},
           /*18*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0},
           /*19*/{0, 0, 0, 0, 0, 0, 0, 0,  0,  0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 15, 8);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 15, 8);

        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_6 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_6 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_7() {
        byte[][] rootGlassMatr = new byte[][]{
            //     0   1  2  3  4  5  6  7  8  9
            /*0*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*1*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*2*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*3*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*4*/{00, 00, 0, 0, 0, 0, 0, 0, 0, 0},
            /*5*/{00,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*6*/{00,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*7*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*8*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*9*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*10*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*11*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*12*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*13*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*14*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*15*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*16*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*17*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*18*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*19*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 4, 0);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 4, 1);

        tof.moveRight(rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_7 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_7 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }

    public void testCaseFigure_3_8() {
        byte[][] rootGlassMatr = new byte[][]{
            //     0   1  2  3  4  5  6  7  8  9
            /*0*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*1*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*2*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*3*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*4*/{00, 00, 0, 0, 0, 0, 0, 0, 0, 0},
            /*5*/{00,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*6*/{00,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*7*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*8*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*9*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*10*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*11*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*12*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*13*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*14*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*15*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*16*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*17*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*18*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*19*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 4, 0);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 4, 8);

        for (int i = 0; i < 20; i++) {
            tof.moveRight(rootGlass);
        }

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            log.debug("testCaseFigure_3_8 is passed!");
            assertTrue(true);
        } else {
            log.error("testCaseFigure_3_8 is not passed");
            log.debug("tof.getFigure():          {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_expected.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }
}
