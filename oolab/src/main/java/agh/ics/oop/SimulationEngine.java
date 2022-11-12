package agh.ics.oop;

import java.util.*;


public class SimulationEngine implements IEngine {
    private final MoveDirection[] directions;
    private final IWorldMap myMap;
    private final Vector2d[] positions;//tylko raz z tego pola korzystamy
    private boolean started = true;
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.myMap = map;
        this.positions = positions;
    }
    public void appendAnimals(){
        if(!this.started)return; //nie przypisujemy wartosci jesli zostaly juz utworzone te pola na poczatku!
        Arrays.stream(positions).forEach(position-> {
            Animal animal = new Animal(this.myMap, position);
            this.myMap.place(animal);//jak zwierze najdzie na inne, to nie zostanie dodane do mapy
        });
        this.started = false;
    }
    @Override
    public void run() {
        this.appendAnimals();
        RectangularMap currentMap = (RectangularMap) this.myMap;
        int animalCounter = currentMap.getAnimals().size();
        int index = 0;
        List<Animal> animals = currentMap.getAnimals();
        for(MoveDirection dir : directions){
            Animal animal = animals.get(index);
            animal.move(dir);
            index = (index + 1) % animalCounter;
            System.out.println(myMap.toString());
        }



    }

}
