package org.zgame.tetris.component.comedowntime;

/**
 * Created by mnikiforov on 31.05.2015.
 */
public class TestComeDownTime implements ComeDownTime {

    @Override
    public long getComeDownTime(int totalPoints) {
        return Long.MAX_VALUE;
    }
}
