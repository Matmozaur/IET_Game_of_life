package agh.cs.lab8.maps;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.utils.Vector2d;

import java.util.List;
import java.util.Map;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {

    void place(Animal animal);

    List<Animal> getAnimals();

    Vector2d targetPosition(Vector2d position);
}
