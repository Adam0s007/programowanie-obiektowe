package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        Vector2d v1 = new Vector2d(1, 9);
        Vector2d v2 = new Vector2d(-1, 2);
        assertEquals("(1,9)",v1.toString());
        assertEquals("(-1,2)",v2.toString());
    }

    @Test
    void precedesTest() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 2);
        Vector2d v3 = new Vector2d(2, 3);

        assertTrue(v1.precedes(v2));
        assertTrue(v2.precedes(v3));
        assertFalse(v3.precedes(v1));

        assertTrue(v3.precedes(v3));//zwrotnosc
    }

    @Test
    void followsTest() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 2);
        Vector2d v3 = new Vector2d(2, 3);

        assertFalse(v1.follows(v2));
        assertFalse(v2.follows(v3));
        assertTrue(v3.follows(v1));

        assertTrue(v3.precedes(v3));//zwrotnosc
    }

    @Test
    void addTest() {
        Vector2d point1 = new Vector2d(1, 0);
        Vector2d point2 = new Vector2d(5, -7);
        Vector2d point3 = new Vector2d(5, 2);
        assertEquals(new Vector2d(6, -7),point1.add(point2));
        assertEquals(new Vector2d(10, -5),point2.add(point3));
        assertEquals(new Vector2d(6, 2),point3.add(point1));
    }

    @Test
    void substractTest() {
        Vector2d point1 = new Vector2d(1, 0);
        Vector2d point2 = new Vector2d(5, -7);
        Vector2d point3 = new Vector2d(5, 2);
        assertEquals(new Vector2d(-4, 7),point1.substract(point2));
        assertEquals(new Vector2d(0, -9),point2.substract(point3));
        assertEquals(new Vector2d(4, 2),point3.substract(point1));
    }

    @Test
    void upperRightTest() {
        Vector2d pos1 = new Vector2d(1, 1);
        Vector2d pos2 = new Vector2d(-1, 4);
        Vector2d pos3 = new Vector2d(2, 3);

        assertEquals(new Vector2d(1, 4),pos1.upperRight(pos2));
        assertEquals(new Vector2d(2, 3),pos1.upperRight(pos3));
        assertEquals(new Vector2d(2, 4),pos3.upperRight(pos2));

        assertEquals(new Vector2d(2, 3),pos3.upperRight(pos3));//zwrotnosc
    }

    @Test
    void lowerLeftTest() {
        Vector2d pos1 = new Vector2d(1, 1);
        Vector2d pos2 = new Vector2d(-1, 4);
        Vector2d pos3 = new Vector2d(2, 3);

        assertEquals(new Vector2d(-1, 1),pos1.lowerLeft(pos2));
        assertEquals(new Vector2d(1, 1),pos1.lowerLeft(pos3));
        assertEquals(new Vector2d(-1, 3),pos3.lowerLeft(pos2));

        assertEquals(new Vector2d(2, 3),pos3.lowerLeft(pos3));//zwrotnosc
    }

    @Test
    void oppositeTest() {
        Vector2d pos1 = new Vector2d(1, 1);
        Vector2d pos2 = new Vector2d(-1, 4);
        Vector2d pos3 = new Vector2d(2, 3);

        assertEquals(new Vector2d(1, 1),pos1.opposite());
        assertEquals(new Vector2d(4, -1),pos2.opposite());
        assertNotEquals(new Vector2d(2, 3),pos3.opposite());
    }

    @Test
    void testEquals() {
        Vector2d pos1 = new Vector2d(1, 0);
        Vector2d pos2 = new Vector2d(2, 0);
        Vector2d pos3 = new Vector2d(1, 0);
        assertFalse(pos1.equals(pos2));

        assertTrue(pos1.equals((pos1)));//zwrotnosc

        assertTrue(pos1.equals((pos3)));


        assertFalse(pos1.equals(MapDirection.WEST.toUnitVector()));
        assertTrue(pos1.equals(MapDirection.EAST.toUnitVector()));
    }
}