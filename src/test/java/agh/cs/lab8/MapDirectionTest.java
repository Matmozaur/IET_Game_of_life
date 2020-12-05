package agh.cs.lab8;
import static org.junit.Assert.assertEquals;

import agh.cs.lab8.maps.MapDirection;
import org.junit.Test;

public class MapDirectionTest {

    @Test
    public void nextForAllCases() {
        assertEquals("next for NORTH must be EAST", MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals("next for EAST must be SOUTH", MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals("next for SOUTH must be WEST", MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals("next for WEST must be NORTH", MapDirection.NORTH, MapDirection.WEST.next());
    }

    @Test
    public void previousForAllCases() {
        assertEquals("previous for EAST must be NORTH", MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals("previous for SOUTH must be EAST", MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals("previous for WEST must be SOUTH", MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals("previous for NORTH must be WEST", MapDirection.WEST, MapDirection.NORTH.previous());
    }
}
