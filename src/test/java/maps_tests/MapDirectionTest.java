package maps_tests;

import agh.cs.lab8.maps.MapDirection;
import agh.cs.lab8.utils.Vector2d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest {

    @Test
    public void nextTests() {
        assertEquals("", MapDirection.NORTHEAST, MapDirection.NORTH.next());
        assertEquals("", MapDirection.SOUTH, MapDirection.SOUTHEAST.next());
        assertEquals("", MapDirection.SOUTHWEST, MapDirection.SOUTH.next());
        assertEquals("", MapDirection.NORTHWEST, MapDirection.WEST.next());
        assertEquals("", MapDirection.EAST, MapDirection.NORTH.next(2));
        assertEquals("", MapDirection.SOUTHWEST, MapDirection.EAST.next(3));
        assertEquals("", MapDirection.SOUTH, MapDirection.SOUTH.next(8));
    }

    @Test
    public void toUnitVectorTests() {
        assertEquals("", new Vector2d(0,1), MapDirection.NORTH.toUnitVector());
        assertEquals("", new Vector2d(1,-1), MapDirection.SOUTHEAST.toUnitVector());
        assertEquals("", new Vector2d(0,-1), MapDirection.SOUTH.toUnitVector());
        assertEquals("", new Vector2d(-1,1), MapDirection.NORTHWEST.toUnitVector());
    }
}
