package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {


    @Test
    void run() {
        //sprawdzanie poprawnosci w przemieszczaniu sie
        String[] arr = {"f", "l", "r","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(arr);
        IWorldMap mapa = new RectangularMap(16, 16);
        Vector2d[] posits = { new Vector2d(1,2), new Vector2d(2, 3)};
        SimulationEngine engine = new SimulationEngine(directions, mapa, posits);
        //uwaga! metoda jest wywolywana tylko raz, wywolywana automatycznie po wywolaniu run()
        List<Animal> animals = mapa.getAnimals();

        assertEquals(animals.get(0).getPosition(), new Vector2d(1,2));
        assertEquals(animals.get(1).getPosition(), new Vector2d(2,3));

        //sprawdzanie poprawnosci w przemieszczaniu sie
        String[] arr2 = {"f", "f", "f", "f","b","b","l","f","f"};
        MoveDirection[] directions2 = new OptionsParser().parse(arr2);
        IWorldMap mapa2 = new RectangularMap(16, 16);
        Vector2d[] posits2 = { new Vector2d(2,2), new Vector2d(2, 3), new Vector2d(2,2)};
        IEngine engine2 = new SimulationEngine(directions2, mapa2, posits2);
        engine2.run();
        List <Animal> animals2 = mapa2.getAnimals();

        assertEquals(animals2.get(0).getPosition(), new Vector2d(1,2));
        assertEquals(animals2.get(1).getPosition(), new Vector2d(2,5));


        //sprawdzanie czy zwierzątka wyjdą poza mapę, ich pozycja koncowa, ich kierunek
        String[] arr3 = {"f","b","b","l","r","b","f","f","f","f"};
        MoveDirection[] directions3 = new OptionsParser().parse(arr3);
        IWorldMap mapa3 = new RectangularMap(16, 16);
        Vector2d[] posits3 = { new Vector2d(1,2), new Vector2d(2, 3), new Vector2d(3,1)};
        IEngine engine3 = new SimulationEngine(directions3, mapa3, posits3);
        engine3.run();
        List <Animal> animals3 = mapa3.getAnimals();

        assertEquals(animals3.get(0).getPosition(), new Vector2d(0,3));
        assertEquals(animals3.get(1).getPosition(), new Vector2d(3,2));
        assertEquals(animals3.get(2).getPosition(), new Vector2d(3,1));
        assertEquals(animals3.get(0).getDirection(), MapDirection.WEST);
        assertEquals(animals3.get(1).getDirection(), MapDirection.EAST);
        assertEquals(animals3.get(2).getDirection(), MapDirection.NORTH);

    }
}