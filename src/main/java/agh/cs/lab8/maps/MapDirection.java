package agh.cs.lab8.maps;

import agh.cs.lab8.utils.Vector2d;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public static List<MapDirection> VALUES = List.of(values());
    public static int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> ("Połnoc");
            case SOUTH -> ("Południe");
            case EAST -> ("Wschód");
            case NORTHEAST -> ("Połnocny wschód");
            case SOUTHEAST -> ("Południowy wschód");
            case SOUTHWEST -> ("Południowy zachód");
            case NORTHWEST -> ("Połnocny zachód");
            default -> ("Zachód");
        };
    }

    public MapDirection next() {
        return switch (this) {
            case NORTH -> (NORTHEAST);
            case NORTHEAST -> (EAST);
            case EAST -> (SOUTHEAST);
            case SOUTHEAST -> (SOUTH);
            case SOUTH -> (SOUTHWEST);
            case SOUTHWEST -> (WEST);
            case WEST -> (NORTHWEST);
            default -> (NORTH);
        };
    }

    public MapDirection next(int n) {
        n = n%8;
        MapDirection direction = this;
        for(int i=0;i<n;i++) {
            direction = direction.next();
        }
        return direction;
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case EAST -> (new Vector2d(1, 0));
            case SOUTH -> (new Vector2d(0, -1));
            case WEST -> (new Vector2d(-1, 0));
            case NORTHEAST -> (new Vector2d(1, 1));
            case SOUTHEAST -> (new Vector2d(-1, 1));
            case SOUTHWEST -> (new Vector2d(-1, -1));
            case NORTHWEST -> (new Vector2d(1, -1));
            default -> (new Vector2d(0, 1));
        };
    }

    public static MapDirection randomDirection()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static List<MapDirection> randomDirections()  {
        Collections.shuffle(VALUES);
        return VALUES;
    }
}
