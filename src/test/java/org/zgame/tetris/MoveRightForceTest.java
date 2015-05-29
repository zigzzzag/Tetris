package org.zgame.tetris;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.RootGlass;
import org.zgame.tetris.component.TemplateOfFigure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by SBT-Nikiforov-MO on 29.05.2015.
 */
public class MoveRightForceTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(MoveRightForceTest.class);
    private RootGlass rootGlass;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        byte[][] rootGlassMatr = new byte[][]{
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0, 00, 00, 0, 0, 0, 0},
                {0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
                {0, 0, 0, 0, 00,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0},
                {0, 0, 0, 0,  0,  0, 0, 0, 0, 0}
        };

        this.rootGlass = new RootGlass(rootGlassMatr);
    }

    public void testCaseFigure3_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TemplateOfFigure tof = new TemplateOfFigure(3, 10, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(3, 10, 5);

        Method method = TemplateOfFigure.class.getDeclaredMethod("moveRightForce", RootGlass.class);
        method.setAccessible(true);
        method.invoke(tof, rootGlass);

        if (Arrays.deepEquals(tof.getFigure(), tof_expected.getFigure())) {
            assertTrue(true);
        } else {
            log.error("testCaseFigure3_1 is not passed");
            log.debug("tof.getFigure():       {}", Arrays.deepToString(tof.getFigure()));
            log.debug("tof_after.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure()));
            assertTrue(false);
        }
    }
}
