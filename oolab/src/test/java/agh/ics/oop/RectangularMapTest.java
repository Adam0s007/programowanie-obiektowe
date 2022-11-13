package agh.ics.oop;

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
        assertFalse(mapa.place(zwierzak1));//nie doda nowego zwierzaka!
        System.out.println(mapa.toString());
        //dzieki fladze started w klasie SimulationEngine mozemy dynamicznie dodawac nowe zwierzeta
        engine.run();
        //ponizej zastosowano przetestowanie zaistnienia nowego zwierzątka na mapie:
        RectangularMap myMap = (RectangularMap) mapa;
        assertEquals(mapa.objectAt(new Vector2d(1, 3)), myMap.getAnimals().get(0));
        assertEquals(mapa.objectAt(new Vector2d(2, 3)), myMap.getAnimals().get(1));
        assertEquals(mapa.objectAt(new Vector2d(2, 2)), myMap.getAnimals().get(2));

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
        RectangularMap myMap = (RectangularMap) mapa;
        ArrayList<Animal> animals = myMap.getAnimals();

        assertNotEquals(mapa.objectAt(new Vector2d(2, 3)), animals.get(0));
        assertEquals(mapa.objectAt(new Vector2d(1, 3)), animals.get(0));
        assertEquals(mapa.objectAt(new Vector2d(2, 3)), animals.get(1));
        assertEquals(mapa.objectAt(new Vector2d(0, 0)),null);

    }
}