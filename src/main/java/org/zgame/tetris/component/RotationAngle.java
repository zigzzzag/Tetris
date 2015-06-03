package org.zgame.tetris.component;

/**
 * Created by mnikiforov on 03.06.2015.
 */
public class RotationAngle {

    private int angle;

    public RotationAngle(int angle) {
        this.angle = angle;
    }

    public void rotate() {
        angle += 90;
        angle = angle % 360;
    }

    public int getCountRotate() {
        return angle / 90;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
