package agh.cs.lab8;
import static org.junit.Assert.assertEquals;

import agh.cs.lab8.maps.MapDirection;
import agh.cs.lab8.utils.MoveDirection;
import agh.cs.lab8.maps.RectangularMap;
import agh.cs.lab8.utils.Vector2d;
import org.junit.Test;

public class AnimalTest {
    private Animal a = new Animal(new RectangularMap(4,4));

    @Test
    public void nextForAllCases() {
        assertEquals("initial position should be (2,2)", new Vector2d(2,2), a.getPosition());
        a.move(MoveDirection.RIGHT);
        assertEquals("orientation should change to EAST", MapDirection.EAST, a.getOrientation());
        a.move(MoveDirection.FORWARD);
        assertEquals("now position should be (3,2)", new Vector2d(3,2), a.getPosition());
    }

}
