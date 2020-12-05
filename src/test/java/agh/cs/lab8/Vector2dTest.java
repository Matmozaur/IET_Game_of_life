package agh.cs.lab8;

import agh.cs.lab8.utils.Vector2d;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {

    private Vector2d v0;
    private Vector2d v1;
    private Vector2d v2;
    private Vector2d v3;
    private Vector2d v4;

    @Before
    public void setUp() {
       v0 = new Vector2d(0,0);
       v1 = new Vector2d(2,1);
       v2 = new Vector2d(1,2);
       v3 = new Vector2d(0,-1);
       v4 = new Vector2d(0,-1);
    }

    @Test
    public void equalsTests() {
        assertFalse("should not be equal", v0.equals(v1));
        assertFalse("should not be equal", v1.equals(v2));
        assertTrue("should be equal", v3.equals(v4));
    }

    @Test
    public void toStringTests() {
        assertEquals("", "(0,0)", v0.toString());
        assertEquals("", "(2,1)", v1.toString());
        assertEquals("", "(0,-1)", v3.toString());
    }

    @Test
    public void precedesTests() {
        assertTrue("", v0.precedes(v1));
        assertFalse("", v1.precedes(v2));
        assertTrue("", v3.precedes(v4));
        assertFalse("", v1.precedes(v3));
    }

    @Test
    public void followsTests() {
        assertTrue("", v1.follows(v0));
        assertFalse("", v1.follows(v2));
        assertTrue("", v3.follows(v4));
        assertTrue("", v1.follows(v3));
    }

    @Test
    public void upperRightTests() {
        assertEquals("", new Vector2d(2,1), v0.upperRight(v1));
        assertEquals("", new Vector2d(2,2), v1.upperRight(v2));
        assertEquals("", new Vector2d(1,2), v3.upperRight(v2));
    }

    @Test
    public void lowerLeftTests() {
        assertEquals("", new Vector2d(0,0), v0.lowerLeft(v1));
        assertEquals("", new Vector2d(1,1), v1.lowerLeft(v2));
        assertEquals("", new Vector2d(0,-1), v3.lowerLeft(v2));
    }

    @Test
    public void addTests() {
        assertEquals("", new Vector2d(2,1), v0.add(v1));
        assertEquals("", new Vector2d(3,3), v1.add(v2));
        assertEquals("", new Vector2d(1,1), v3.add(v2));
    }

    @Test
    public void subtractTests() {
        assertEquals("", new Vector2d(-2,-1), v0.subtract(v1));
        assertEquals("", new Vector2d(1,-1), v1.subtract(v2));
        assertEquals("", new Vector2d(-1,-3), v3.subtract(v2));
    }

    @Test
    public void oppositeTests() {
        assertEquals("", new Vector2d(0,0), v0.opposite());
        assertEquals("", new Vector2d(-2,-1), v1.opposite());
        assertEquals("", new Vector2d(0,1), v3.opposite());
    }
}
