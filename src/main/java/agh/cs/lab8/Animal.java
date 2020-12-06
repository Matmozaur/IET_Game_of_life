package agh.cs.lab8;

import agh.cs.lab8.maps.*;
import agh.cs.lab8.utils.Config;
import agh.cs.lab8.utils.DNAUtils;
import agh.cs.lab8.utils.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    private int energy;
    private final List<Integer> dna;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();

    public Animal(AbstractWorldMap map, int energy) {
        this.map = map;
        this.observers.add(map);
        this.position = new Vector2d(ThreadLocalRandom.current().nextInt(0, map.getWidth()),
                ThreadLocalRandom.current().nextInt(0,map.getHeight()));
        this.orientation = MapDirection.randomDirection();
        this.dna = DNAUtils.drawDNA();
    }

    public Animal(AbstractWorldMap map, int energy, Vector2d initialPosition, List<Integer> dna) {
        this.map = map;
        this.observers.add(map);
        this.position = initialPosition;
        this.orientation = MapDirection.randomDirection();
        this.dna = dna;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public List<Integer> getDna() {
        return dna;
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

    public void move() {
        Vector2d oldPosition = this.position;
        Vector2d future_position = map.targetPosition(this.position.add(this.orientation.toUnitVector()));
        if (this.map.canMoveTo(future_position)) {
            this.position = future_position;
            this.positionChanged(oldPosition, future_position);
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

    void dead() {
        for (IPositionChangeObserver o:this.observers) {
            o.dead();
        }
    }

    public void useEnergy(){
        this.energy -= 1;
        if(this.energy == 0) {
            dead();
        }
    }

    public void rotate(){
        int rand_idx = ThreadLocalRandom.current().nextInt(0,Config.dnaLength+1);
        this.orientation = this.orientation.next(this.dna.get(rand_idx));
    }

    public static Animal reproduce(Animal animal1, Animal animal2) {
        int energy = animal1.energy/2+animal2.energy/2;
        animal1.energy -= animal1.energy/2;
        animal2.energy -= animal2.energy/2;
        Vector2d position = animal1.position;
        for(MapDirection d:MapDirection.randomDirections()) {
            Vector2d future_position = animal1.map.targetPosition(position.add(d.toUnitVector()));
            if (animal1.map.canMoveTo(future_position)) {
                position = future_position;
                break;
            }
        }
        return new Animal(animal1.map, animal1.energy/2+animal2.energy/2, position, DNAUtils.recombineDNA(animal1.dna, animal2.dna));
    }
}
