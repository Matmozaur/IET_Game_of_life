package agh.cs.lab8.map_elements;

import agh.cs.lab8.utils.Vector2d;

public class Plant implements IMapElement {
    protected Vector2d position;
    protected int energy;

    public Plant(Vector2d position, int energy) {
        this.position = position;
        this.energy = energy;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
