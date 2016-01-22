package org.zgame.components.particles;

/**
 * Created by sbt-nikiforov-mo on 21.01.16.
 */
public class Line {

    protected double xStart;
    protected double yStart;
    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;
    protected double v_x;
    protected double v_y;
    protected int ticks;
    protected int r;
    protected int g;
    protected int b;

    public Line(int x, int y, double v_x, double v_y) {
        this.xStart = x;
        this.yStart = y;

        this.x1 = x;
        this.y1 = y;
        this.x2 = x;
        this.y2 = y;

        this.v_x = v_x;
        this.v_y = v_y;
        this.r = (int) (Math.random() * 256);
        this.g = (int) (Math.random() * 256);
        this.b = (int) (Math.random() * 256);
        this.ticks = 0;
    }

    public void tick() {
        x2 = x1;
        y2 = y1;
        x1 += v_x;
        y1 += v_y;

        ticks += 1;

        v_x = v_x * 0.99;

        v_y += 0.2;
        v_y = v_y * 0.99;
    }

    public void reset() {
        this.x1 = this.xStart;
        this.y1 = this.yStart;

        this.x2 = this.xStart;
        this.y2 = this.yStart;
    }

    public double getxStart() {
        return xStart;
    }

    public double getyStart() {
        return yStart;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getV_x() {
        return v_x;
    }

    public void setV_x(double v_x) {
        this.v_x = v_x;
    }

    public double getV_y() {
        return v_y;
    }

    public void setV_y(double v_y) {
        this.v_y = v_y;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    private static final String PF = "%.2f";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LINE_").append(String.format("%-10d", hashCode())).append("[")
                .append("x1=")    .append(String.format(PF, x1))    .append(",")
                .append("x2=")    .append(String.format(PF, x2))    .append(",")
                .append("y1=")    .append(String.format(PF, y1))    .append(",")
                .append("y2=")    .append(String.format(PF, y2))    .append(",")
                .append("xStart=").append(String.format(PF, xStart)).append(",")
                .append("yStart=").append(String.format(PF, yStart)).append(",")
                .append("ticks=") .append(ticks)                    .append(",")
                .append("v_x=")   .append(String.format(PF, v_x))   .append(",")
                .append("v_y=")   .append(String.format(PF, v_y))
                .append("]");
        return sb.toString();
    }
}
