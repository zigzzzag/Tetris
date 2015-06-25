package org.zgame.tetris;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.tetris.component.FigureType;
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
        TemplateOfFigure tof = new TemplateOfFigure(FigureType.G, 10, 4);
        TemplateOfFigure tof_expected = new TemplateOfFigure(FigureType.G, 10, 5);

        Method method = TemplateOfFigure.class.getDeclaredMethod("moveRightForce");
        method.setAccessible(true);
        method.invoke(tof);

        if (Arrays.deepEquals(tof.getFigure().getMatr(), tof_expected.getFigure().getMatr())) {
            assertTrue(true);
        } else {
            log.error("testCaseFigure3_1 is not passed");
            log.debug("tof.getFigure().getMatr():       {}", Arrays.deepToString(tof.getFigure().getMatr()));
            log.debug("tof_after.getFigure(): {}", Arrays.deepToString(tof_expected.getFigure().getMatr()));
            assertTrue(false);
        }
    }
}
