package agh.cs.lab8;

import agh.cs.lab8.maps.*;
import agh.cs.lab8.utils.MoveDirection;
import agh.cs.lab8.utils.OptionsParser;
import agh.cs.lab8.utils.Vector2d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {

    @Test
    public void rectangularMapRunTest() {
        String[] args = new String[] {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        AbstractWorldMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));
        IEngine engine = new WorldEngine(map);
        map.place(animal1);
        map.place(animal2);
        engine.run(directions);
        assertEquals("should return first animal", animal1, map.objectAt(new Vector2d(2,4)));
        assertEquals("should return secound animal", animal2, map.objectAt(new Vector2d(3,2)));
        assertEquals("should be South", animal1.getOrientation(), MapDirection.SOUTH);
        assertEquals("should be NORTH", animal2.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void grassMapRunTest() {
        String[] args = new String[] {"f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        AbstractWorldMap map = new GrassField(10, 5, 50);
        Animal animal = new Animal(map,new Vector2d(3,4));
        IEngine engine = new WorldEngine(map);
        map.place(animal);
        engine.run(directions);
        assertEquals("should return animal", animal, map.objectAt(new Vector2d(3,1)));
        assertEquals("should be NORTH", animal.getOrientation(), MapDirection.NORTH);
        assertTrue("should be grass", map.objectAt(new Vector2d(0, 1)) instanceof Grass);
    }

}
