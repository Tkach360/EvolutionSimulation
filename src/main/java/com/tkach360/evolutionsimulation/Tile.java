package com.tkach360.evolutionsimulation;

import java.util.Objects;
/** описывает логику тайла - одного поля, в котором могут находиться объекты */
public class Tile {

    private Field field;
    private int cx; // координата X
    private int cy; // координата Y
    private AbstractTileObject abstractTileObject; // ссылка на объект, который находится в тайле

    private int soilEnergy; // энергия в почве тайла
    private int lighting; // освещенность тайла

    public Tile(int cx, int cy, int soilEnergy, int lighting) {
        this.cx = cx;
        this.cy = cy;
        this.field = new Field(TileMap.TILE_SIDE * cx, TileMap.TILE_SIDE * cy);
        setSoilEnergy(soilEnergy);
        setLighting(lighting);
        abstractTileObject = null;
    }

    public int getSoilEnergy() {
        return soilEnergy;
    }
    public void setSoilEnergy(int soilEnergy) {
        this.soilEnergy = NumRangeController.setInRange(soilEnergy, 0, 4);
    }

    public int getLighting() {
        return lighting;
    }
    public void setLighting(int lighting) {
        this.lighting = NumRangeController.setInRange(lighting, 0, 4);
    }

    public int getCx() {
        return cx;
    }
    public int getCy() {
        return cy;
    }

    public AbstractTileObject getAbstractTileObject() {
        return abstractTileObject;
    }
    public void setAbstractTileObject(AbstractTileObject abstractTileObject) {
        this.abstractTileObject = abstractTileObject;
    }

    public Field getField() {
        return field;
    }

    public void changeLighting(int value){
        setLighting(this.lighting + value);
    }

    public void changeSoil(int value){
        setSoilEnergy(this.soilEnergy + value);
    }

    // TODO: изменить стандартные методы

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
