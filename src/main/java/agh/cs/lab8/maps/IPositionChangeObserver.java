package agh.cs.lab8.maps;

import agh.cs.lab8.utils.Vector2d;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
    void dead();
}
