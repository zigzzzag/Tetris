package org.zgame.tetris.component.comedowntime;

/**
 * Created by mnikiforov on 31.05.2015.
 */
public class ComeDownTimeImpl implements ComeDownTime {

    @Override
    public long getComeDownTime(int totalPoints) {
//        if (1000 - totalPoints / 50 > 200) {
//            return 1000 - totalPoints / 50;
//        }
        return 2000;
    }
}
