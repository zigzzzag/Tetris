package org.zgame.tetris;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.RootGlass;
import org.zgame.tetris.component.TemplateOfFigure;

import java.util.Arrays;

/**
 * Created by SBT-Nikiforov-MO on 01.06.2015.
 */
public class MoveDownGTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(MoveDownGTest.class);

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
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 10, 4);

        tof.moveDown(rootGlass);

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
           /*10*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  1,  1, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 10, 4);

        tof.moveDown(rootGlass);

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
           /*10*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  1,  1, 0, 0, 0, 0},
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

        tof.moveDown(rootGlass);

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
           /*10*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  1,  1, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 10, 4);

        tof.moveDown(rootGlass);
        tof.moveDown(rootGlass);

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
           /*10*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
           /*12*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*13*/{0, 0, 0, 0,  1,  1, 0, 0, 0, 0},
           /*14*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*15*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*16*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*17*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*18*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*19*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 9, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 10, 4);

        for (int i = 0; i < 10; i++) {
            tof.moveDown(rootGlass);
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
            //    0   1   2  3  4  5  6  7  8  9
            /*0*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*1*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*2*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*3*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*4*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*5*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*6*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*7*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*8*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
            /*9*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*10*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*11*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*12*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*13*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*14*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*15*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0},
           /*16*/{0, 00, 00, 0, 0, 0, 0, 0, 0, 0},
           /*17*/{0, 00,  0, 0, 0, 0, 0, 0, 0, 0},
           /*18*/{0, 00,  0, 0, 0, 0, 0, 0, 0, 0},
           /*19*/{0,  0,  0, 0, 0, 0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 16, 1);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 17, 1);

        tof.moveDown(rootGlass);

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
            //     0   1  2  3  4  5  6  7  8  9
            /*0*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*1*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*2*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*3*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*4*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*5*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*6*/{ 0,  0, 0, 0, 0, 0, 0, 0, 0, 0},
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
           /*17*/{00, 00, 0, 0, 0, 0, 0, 0, 0, 0},
           /*18*/{00,  0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*19*/{00,  0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        RootGlass rootGlass = new RootGlass(rootGlassMatr);
        TemplateOfFigure tof = new TemplateOfFigure(3, 17, 0);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 17, 0);

        tof.moveDown(rootGlass);

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
            //    0  1  2  3   4   5  6  7  8  9
            /*0*/{0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
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
        TemplateOfFigure tof = new TemplateOfFigure(3, 0, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 1, 4);

        tof.moveDown(rootGlass);

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
            //    0  1  2  3   4   5  6  7  8  9
            /*0*/{0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
            /*1*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
            /*2*/{0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
            /*3*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*4*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*5*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*6*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*7*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*8*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
            /*9*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*10*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
           /*11*/{0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
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
        TemplateOfFigure tof = new TemplateOfFigure(3, 0, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 17, 4);

        for (int i = 0; i < 30; i++) {
            tof.moveDown(rootGlass);
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
