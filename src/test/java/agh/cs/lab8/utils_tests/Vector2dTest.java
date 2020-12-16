package agh.cs.lab8.utils_tests;

import agh.cs.lab8.utils.Vector2d;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
        assertNotEquals("should not be equal", v0, v1);
        assertNotEquals("should not be equal", v1, v2);
        assertEquals("should be equal", v3, v4);
    }

    @Test
    public void toStringTests() {
        assertEquals("", "(0,0)", v0.toString());
        assertEquals("", "(2,1)", v1.toString());
        assertEquals("", "(0,-1)", v3.toString());
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
