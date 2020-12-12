package agh.cs.lab8.maps;

import agh.cs.lab8.map_elements.Animal;
import agh.cs.lab8.utils.Vector2d;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
    void dead(Animal animal);
}
