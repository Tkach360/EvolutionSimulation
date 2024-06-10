package com.tkach360.evolutionsimulation;

/** класс, содержащий координаты на канвасе */
public class Field {
    private double cx;
    private double cy;

    public Field(double cx, double cy) {
        this.cx = cx;
        this.cy = cy;
    }

    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }
}
