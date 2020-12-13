package agh.cs.lab8.maps;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.utils.Vector2d;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected int width;
    protected int height;
    protected List<Animal> animals;
    protected List<Animal> graveyard;
    protected Map<Vector2d, List<Animal>> animalsPositions;

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.animalsPositions = new ConcurrentHashMap<>();
        this.animals = new LinkedList<>();
        this.graveyard = new LinkedList<>();
    }

    public Map<Vector2d, List<Animal>> getAnimalsPositions() {
        return animalsPositions;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void place(Animal animal) {
            this.animals.add(animal);
        this.animalsPositions.computeIfAbsent(animal.getPosition(), k -> new LinkedList<>());
            this.animalsPositions.get(animal.getPosition()).add(animal);
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
            if(position.getY() < height && position.getY() >= 0) {
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
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        this.animalsPositions.get(oldPosition).remove(animal);
        List<Animal> elements = this.animalsPositions.get(newPosition);
        if(elements==null) {
            this.animalsPositions.put(newPosition, new LinkedList<>());
            this.animalsPositions.get(newPosition).add(animal);
        }
        else {
            elements.add(animal);
        }
    }

    @Override
    public void dead(Animal animal) {
        this.animalsPositions.get(animal.getPosition()).remove(animal);
        this.animals.remove(animal);
        this.graveyard.add(animal);
    }

    public boolean isOccupied(Vector2d position) {
        if(this.animalsPositions.get(position) == null) {
            return false;
        }
        else {
            if(this.animalsPositions.get(position).size() == 0) {
                return false;
            }
            else {
                return true;
            }
        }
    }
}
