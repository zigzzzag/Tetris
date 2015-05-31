package org.zgame.tetris.component.comedowntime;

/**
 * Created by mnikiforov on 31.05.2015.
 */
public interface ComeDownTime {

    //1 клетка за getComeDownTime секунд
    public long getComeDownTime(int totalPoints);
}
