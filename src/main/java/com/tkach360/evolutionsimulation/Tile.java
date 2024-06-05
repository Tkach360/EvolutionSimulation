package com.tkach360.evolutionsimulation;

import java.util.Objects;

public class Tile {

    private double cx; // координата X
    private double cy; // координата Y

    private double soilEnergy; // энергия в почве тайла
    private double lighting; // освещенность тайла

    public Tile(double cx, double cy, double soilEnergy, double lighting) {
        this.cx = cx;
        this.cy = cy;
        this.soilEnergy = soilEnergy;
        this.lighting = lighting;
    }

    public double getSoilEnergy() {
        return soilEnergy;
    }

    public void setSoilEnergy(double soilEnergy) {
        this.soilEnergy = soilEnergy;
    }

    public double getLighting() {
        return lighting;
    }

    public void setLighting(double lighting) {
        if(lighting < 0) lighting = 0;
        if(lighting > 1) lighting = 1;
        this.lighting = lighting;
    }

    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Double.compare(cx, tile.cx) == 0 && Double.compare(cy, tile.cy) == 0 && Double.compare(soilEnergy, tile.soilEnergy) == 0 && Double.compare(lighting, tile.lighting) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cx, cy, soilEnergy, lighting);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tile{");
        sb.append("cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", soilEnergy=").append(soilEnergy);
        sb.append(", lighting=").append(lighting);
        sb.append('}');
        return sb.toString();
    }
}
