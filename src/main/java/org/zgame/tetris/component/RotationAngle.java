package org.zgame.tetris.component;

/**
 * Created by mnikiforov on 03.06.2015.
 */
public class RotationAngle implements Cloneable {

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

    @Override
    public RotationAngle clone() {
        try {
            return (RotationAngle) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
