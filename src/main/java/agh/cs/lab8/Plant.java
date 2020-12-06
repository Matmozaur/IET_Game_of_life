package agh.cs.lab8;

import agh.cs.lab8.utils.Vector2d;

public class Plant {
    Vector2d position;
    int energy;

    public Plant(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "*";
    }
}
