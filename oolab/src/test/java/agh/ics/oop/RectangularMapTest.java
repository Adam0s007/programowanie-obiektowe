package agh.ics.oop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class RectangularMapTest {
    String[] arr = {"f", "l", "r","f","f"}; //argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    MoveDirection[] directions = new OptionsParser().parse(arr);
    IWorldMap mapa = new RectangularMap(16, 16);
    Vector2d[] posits = { new Vector2d(1,2), new Vector2d(2, 3)};//argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    IEngine engine = new SimulationEngine(directions, mapa, posits);

    @Test
    void canMoveTo() {
        engine.run();
        assertTrue(mapa.canMoveTo(new Vector2d(2, 2)));
        assertFalse(mapa.canMoveTo(new Vector2d(2, 3)));
        assertFalse(mapa.canMoveTo(new Vector2d(1, 3)));
    }
    @Test
    void place() {
        engine.run();
        Animal zwierzak = new Animal(mapa, new Vector2d(2, 2));
        Animal zwierzak1 = new Animal(mapa, new Vector2d(1, 3));
        assertTrue(mapa.place(zwierzak)); //doda nowe zwierzę!
        System.out.println(mapa.toString());
        Assertions.assertThrows(IllegalArgumentException.class, ()->mapa.place(zwierzak1));
        System.out.println(mapa.toString());



    }

    @Test
    void isOccupied() {
        engine.run();
        assertFalse(mapa.isOccupied(new Vector2d(2, 2)));
        assertTrue(mapa.isOccupied(new Vector2d(2, 3)));
        assertTrue(mapa.isOccupied(new Vector2d(1, 3)));
    }

    
    @Test
    void objectAt() {
        engine.run();
        HashMap<Vector2d,Animal> animals = mapa.getAnimals();
        Vector2d vector0 = new Vector2d(2, 3);
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(2, 3);
        Vector2d vector3 = new Vector2d(0, 0);
        assertNotEquals(mapa.objectAt(vector0), animals.get(vector1));
        assertEquals(mapa.objectAt(vector1), animals.get(vector1));
        assertEquals(mapa.objectAt(vector2), animals.get(vector2));
        assertEquals(mapa.objectAt(vector3),null);

    }
}