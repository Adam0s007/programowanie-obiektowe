package agh.ics.oop;

import java.util.*;


public class SimulationEngine implements IEngine {
    private final MoveDirection[] directions;
    private final IWorldMap myMap;
    private final Vector2d[] positions;//tylko raz z tego pola korzystamy
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.myMap = map;
        this.positions = positions;

        Arrays.stream(positions).forEach(position-> {
            Animal animal = new Animal(this.myMap, position);
            this.myMap.place(animal);//jak zwierze najdzie na inne, to nie zostanie dodane do mapy
        });
        System.out.println(myMap.toString());
    }

    @Override
    public void run() {

        int animalCounter = this.myMap.getAnimals().size();
        int index = 0;
        ArrayList<Animal> animals = this.myMap.getAnimals();
        //tworzymy
        //JframeOutput framek = new JframeOutput(animals,mapa);
        for(MoveDirection dir : directions){
            Animal animal = animals.get(index);
            animal.move(dir);
            //framek.setPositions(animals,index);
            index = (index + 1) % animalCounter;
            System.out.println(this.myMap);
        }

        //framek.toSleep(2000);
        //framek.discard();



    }

}
