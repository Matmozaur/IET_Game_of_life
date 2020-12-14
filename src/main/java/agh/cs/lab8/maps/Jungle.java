package agh.cs.lab8.maps;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.map_elements.Plant;
import agh.cs.lab8.utils.Vector2d;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Jungle extends AbstractWorldMap {

    private final Map<Vector2d, Plant> plants = new ConcurrentHashMap<>();
    protected Vector2d jungleLeftDown;
    protected Vector2d jungleRightUp;
    protected int startEnergy;
    protected int plantEnergy;
    public int day = 0;


    public Jungle(int width, int height, Vector2d jungleLeftDown, Vector2d jungleRightUp, int plantEnergy, int nPlants,
                  int startEnergy, int nAnimals) {
        super(width, height);
        this.jungleLeftDown = jungleLeftDown;
        this.jungleRightUp = jungleRightUp;
        this.startEnergy = startEnergy;
        this.plantEnergy = plantEnergy;
        this.initPlants(nPlants);
        this.initAnimals(nAnimals);
    }

    public Vector2d getJungleLeftDown() {
        return jungleLeftDown;
    }

    public Vector2d getJungleRightUp() {
        return jungleRightUp;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public Map<Vector2d, Plant> getPlants() {
        return plants;
    }

    private void initPlants(int n) {
        List<Integer> places = ThreadLocalRandom.current().ints(0,
                this.height*this.width).distinct().limit(n).boxed().collect(Collectors.toList());
        for(int i:places) {
            Vector2d v = new Vector2d(i/this.height,i%this.height);
            this.plants.put(v,new Plant(v, this.plantEnergy));
        }
    }

    private void initAnimals(int n) {
        List<Integer> places = ThreadLocalRandom.current().ints(0,
                this.height*this.width).distinct().limit(n).boxed().collect(Collectors.toList());
        for(int i:places) {
            Vector2d v = new Vector2d(i/this.height,i%this.height);
            Animal animal = new Animal(this, this.startEnergy, v);
            this.animals.add(animal);
            this.animalsPositions.put(v, new LinkedList<>());
            this.animalsPositions.get(v).add(animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(super.isOccupied(position)) {
            return true;
        }
        else {
            return this.plants.get(position) != null;
        }
    }

    public void addNewPlants() {
        List<Integer> places = ThreadLocalRandom.current().ints(0,
                this.height*this.width).distinct().limit((long) this.height *this.width).boxed().collect(Collectors.toList());
        boolean flag1=false, flag2=false;
        for(int i:places) {
            Vector2d v = new Vector2d(i/this.height,i%this.height);
            if(!this.isOccupied(v)) {
                if(v.getX() < this.jungleLeftDown.getX() || v.getX() > this.jungleRightUp.getX() || v.getY() < this.jungleLeftDown.getY() || v.getY() > this.jungleRightUp.getY()) {
                    if(!flag1) {
                        this.plants.put(v,new Plant(v, this.plantEnergy));
                        flag1 = true;
                    }
                }
                else {
                    if(!flag2) {
                        this.plants.put(v,new Plant(v, this.plantEnergy));
                        flag2 = true;
                    }
                }
            }
        }
    }

    public void removeDeadAnimals() {
        day += 1;
        for (int i = 0; i < this.animals.size(); i++)
        {
            animals.get(i).useEnergy();
        }
    }

    public void moveAnimals() {
        for(Animal animal:this.animals) {
            animal.rotate();
            animal.move();
        }
    }

    public void eating() {
        for (Map.Entry<Vector2d,Plant> entry : this.plants.entrySet()){
            List<Animal> animalsAtPlace = this.animalsPositions.get(entry.getKey());
            if(animalsAtPlace != null) {
                if(animalsAtPlace.size() != 0) {
                    animalsAtPlace = animalsAtPlace.stream()
                            .collect(Collectors.groupingBy(
                                    Animal::getEnergy,
                                    TreeMap::new,
                                    Collectors.toList()
                            ))
                            .lastEntry()
                            .getValue();
                    int energy = this.plantEnergy/animalsAtPlace.size();
                    for(Animal animal:animalsAtPlace) {
                        animal.addEnergy(energy);
                    }
                    this.plants.remove(entry.getKey());
                }
            }
        }
    }

    public void mating() {
        long i = 0;
        Iterator<Map.Entry<Vector2d,List<Animal>>> it = this.animalsPositions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Vector2d,List<Animal>> entry = it.next();
            List<Animal> animalsAtPlace = this.animalsPositions.get(entry.getKey());
            if(animalsAtPlace != null) {
                if(animalsAtPlace.size() >= 2) {
                    animalsAtPlace.sort(Comparator.comparing(Animal::getEnergy));
                    Collections.reverse(animalsAtPlace);
                    if(animalsAtPlace.get(1).getEnergy() >= this.startEnergy/2) {
                        this.place(Animal.reproduce(animalsAtPlace.get(0), animalsAtPlace.get(1)));
                    }
                }
            }
        }
    }
}
