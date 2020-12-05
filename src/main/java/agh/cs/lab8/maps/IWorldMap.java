package agh.cs.lab8.maps;

import agh.cs.lab8.Animal;
import agh.cs.lab8.utils.Vector2d;

import java.util.Map;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(Animal animal);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);

    Map<Vector2d, Animal> getAnimals();

    Vector2d targetPosition(Vector2d position);
}
