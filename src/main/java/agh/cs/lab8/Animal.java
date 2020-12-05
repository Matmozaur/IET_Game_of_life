package agh.cs.lab8;

import agh.cs.lab8.maps.*;
import agh.cs.lab8.utils.MoveDirection;
import agh.cs.lab8.utils.Vector2d;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();

    public Animal(AbstractWorldMap map) {
        this.map = map;
        this.observers.add(map);
        this.position = new Vector2d(2,2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(AbstractWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.observers.add(map);
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        switch (orientation) {
            case NORTH -> {
                return "N";
            }
            case EAST -> {
                return "E";
            }
            case WEST -> {
                return "W";
            }
            default -> {
                return "S";
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        agh.cs.lab8.Animal animal = (agh.cs.lab8.Animal) o;
        return map == animal.map &&
                orientation == animal.orientation &&
                Objects.equals(position, animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }

    public void move(MoveDirection direction) {
        Vector2d future_position;
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d oldPosition = this.position;
                future_position = map.targetPosition(this.position.add(this.orientation.toUnitVector()));
                if (this.map.canMoveTo(future_position)) {
                    this.position = future_position;
                    this.positionChanged(oldPosition, future_position);
                }
            }
            default -> {
                Vector2d oldPosition = this.position;
                future_position = map.targetPosition(this.position.subtract(this.orientation.toUnitVector()));
                if (this.map.canMoveTo(future_position)) {
                    this.position = future_position;
                    this.positionChanged(oldPosition, future_position);
                }
            }
        }
    }

    void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver o:this.observers) {
            o.positionChanged(oldPosition, newPosition);
        }
    }
}
