package agh.cs.lab8;

import agh.cs.lab8.maps.AbstractWorldMap;
import agh.cs.lab8.utils.MoveDirection;

public class WorldEngine implements IEngine {

    private final AbstractWorldMap worldMap;

    public WorldEngine(AbstractWorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void run(MoveDirection[] directions) {
        Animal[] animals = worldMap.getAnimals().values().toArray(new Animal[0]);
        for(int i=0;i<directions.length;i++) {
            if(directions[i] != null) {
                Animal animal = animals[i%animals.length];
                animal.move(directions[i]);
            }
        }
    }

}
