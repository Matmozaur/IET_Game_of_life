package agh.cs.lab8.maps;

import agh.cs.lab8.Animal;
import agh.cs.lab8.utils.Vector2d;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected int width;
    protected int height;
    protected Map<Vector2d, Animal> animals;

    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.animals = new LinkedHashMap<>();
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public boolean place(Animal animal) {
        if(!isOccupied(animal.getPosition())) {
            this.animals.put(animal.getPosition(),animal);
            return(true);
        }
        throw new IllegalArgumentException(animal.getPosition() + " is occupied!");
    }

    public boolean isOccupied(Vector2d position) {
        return(objectAt(position) instanceof Animal);
    }

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public Vector2d targetPosition(Vector2d position) {
        if(position.getX() < width && position.getX() >= 0) {
            if(position.getY() < height && position.getY() >= 0) {
                return position;
            }
            else {
                int y = position.getY()%height;
                if (y<0) y += height;
                return new Vector2d(position.getX(), y);
            }
        }
        else {
            if(position.getX() < height && position.getY() >= 0) {
                int x = position.getX()%width;
                if (x<0) x += width;
                return new Vector2d(x, position.getY());
            }
            else {
                int x = position.getX()%width;
                if (x<0) x += width;
                int y = position.getY()%height;
                if (y<0) y += height;
                return new Vector2d(x, y);
            }
        }
    }

    public Vector2d[] borders() {
        return new Vector2d[] {new Vector2d(0,0), new Vector2d(this.width, this.height)};
    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        Vector2d[] borders = borders();
        return (mv.draw(borders[0], borders[1]));
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }
}
