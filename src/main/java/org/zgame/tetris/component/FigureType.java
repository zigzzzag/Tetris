package org.zgame.tetris.component;

import java.util.Random;

/**
 * Created by mnikiforov on 30.05.2015.
 */
public enum FigureType {

    S(1),
    S_R(2),
    G(3),
    G_R(4),
    T(5),
    QUADRATE(6),
    STICK(7);

    private int value;

    FigureType(int intVal) {
        this.value = intVal;
    }

    public static FigureType getTypeByIntVal(int val) {
        for (FigureType type : FigureType.values()) {
            if (type.getValue() == val) {
                return type;
            }
        }

        return null;
    }

    public static FigureType randomType() {
        return getTypeByIntVal(new Random().nextInt(FigureType.values().length) + 1);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
