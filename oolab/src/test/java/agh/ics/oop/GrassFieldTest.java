package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;

class GrassFieldTest {
    Random randomizer = ThreadLocalRandom.current();

    String[] arr = {"f", "l", "r","f","f","r","r","f","l","f","f","f","f","f","f","f","f","f","f","f"}; //argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    MoveDirection[] directions = new OptionsParser().parse(arr);
    IWorldMap mapa = new GrassField(12);
    Vector2d[] posits = { new Vector2d(1,2), new Vector2d(2, 3)};//argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    IEngine engine = new SimulationEngine(directions, mapa, posits);

    @Test
    void checkUniq(){ //sprawdza czy pola zawierajace trawe są unikatowe
        ArrayList<Boolean> checker = new ArrayList<>();
        for(int i =0;i < 20;i++){
            IWorldMap grassField = new GrassField(randomizer.nextInt(20)+1);
            HashMap<String,Boolean> counter = new HashMap<String,Boolean>();
            grassField.getGrasses().stream().forEach(grass->{
                assertFalse(counter.containsKey(grass.getPosition().toString()));
                counter.put(grass.getPosition().toString(),true);
            });
        }
    }

    @Test
    void isOccupied() {
    engine.run();
    assertTrue(mapa.isOccupied(new Vector2d(6,3)));
    assertTrue(mapa.isOccupied(new Vector2d(2,10)));
    //nie mozemy sprawdzic innych pozycji, bo nie wiemy gdzie bedzie trawa, ewentualnie poza pole wystepowania trawy
        assertFalse(mapa.isOccupied(new Vector2d(-1,-1)));
        assertFalse(mapa.isOccupied(new Vector2d(20,20)));
    }

    @Test
    void objectAt() {
        engine.run();
        assertTrue(mapa.objectAt(new Vector2d(6,3)) instanceof Animal);
        assertTrue(mapa.objectAt(new Vector2d(2,10)) instanceof Animal);
        assertFalse(mapa.objectAt(new Vector2d(0,10)) instanceof Animal);
    }
}