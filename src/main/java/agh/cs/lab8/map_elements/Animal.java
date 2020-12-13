package agh.cs.lab8.map_elements;

import agh.cs.lab8.maps.*;
import agh.cs.lab8.utils.Config;
import agh.cs.lab8.utils.DNAUtils;
import agh.cs.lab8.utils.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IMapElement {
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    private int energy;
    public static int moveEnergy=1;
    private int id;
    private int daysAlive=0;
    private final List<Integer> dna;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();
    private final List<Animal> parents = new LinkedList<>();
    private final List<Animal> kids = new LinkedList<>();


    public Animal(AbstractWorldMap map, int energy, Vector2d initialPosition) {
        this.map = map;
        this.observers.add(map);
        this.position = initialPosition;
        this.orientation = MapDirection.randomDirection();
        this.dna = DNAUtils.drawDNA();
        this.energy = energy;
        this.id = map.getCounter();
        map.updateCounter();
    }

    public Animal(AbstractWorldMap map, int energy, Vector2d initialPosition, List<Integer> dna, Animal parent1,
                  Animal parent2) {
        this.map = map;
        this.observers.add(map);
        this.position = initialPosition;
        this.orientation = MapDirection.randomDirection();
        this.dna = dna;
        this.energy = energy;
        this.parents.add(parent1);
        this.parents.add(parent2);
        this.id = map.getCounter();
        map.updateCounter();
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public List<Integer> getDna() {
        return dna;
    }

    public List<Animal> getKids() {
        return kids;
    }

    public int getDaysAlive() {
        return daysAlive;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
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
        this.position = future_position;
        this.positionChanged(oldPosition, future_position, this);
    }

    void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        for (IPositionChangeObserver o:this.observers) {
            o.positionChanged(oldPosition, newPosition, this);
        }
    }

    void dead() {
        for (IPositionChangeObserver o:this.observers) {
            o.dead(this);
        }
    }

    public void useEnergy(){
        this.daysAlive += 1;
        this.energy -= moveEnergy;
        if(this.energy == 0) {
            dead();
        }
    }

    public void rotate(){
        int rand_idx = ThreadLocalRandom.current().nextInt(0,Config.dnaLength);
        this.orientation = this.orientation.next(this.dna.get(rand_idx));
    }

    public static Animal reproduce(Animal animal1, Animal animal2) {
        int energy = animal1.energy/4+animal2.energy/4;
        animal1.energy -= 3*animal1.energy/4;
        animal2.energy -= 3*animal2.energy/4;
        Vector2d position = animal1.position;
//        TODO
//        for(MapDirection d:MapDirection.randomDirections()) {
//            Vector2d future_position = animal1.map.targetPosition(position.add(d.toUnitVector()));
//            if (animal1.map.isOccupied(future_position)) {
//                position = future_position;
//                break;
//            }
//        }
        position = animal1.map.targetPosition(position.add(MapDirection.randomDirections().get(0).toUnitVector()));
        Animal child = new Animal(animal1.map, animal1.energy/2+animal2.energy/2, position,
                DNAUtils.recombineDNA(animal1.dna, animal2.dna), animal1, animal2);
        animal1.kids.add(child);
        animal2.kids.add(child);
        return new Animal(animal1.map, animal1.energy/2+animal2.energy/2, position,
                DNAUtils.recombineDNA(animal1.dna, animal2.dna), animal1, animal2);
    }

    public void addEnergy(int energy) {
        this.energy += energy;
    }

    public String getDnaString() {
        return dna.toString();
    }

}
