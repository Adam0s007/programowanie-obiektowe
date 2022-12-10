package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Platform;
import java.util.*;


public class SimulationEngine implements IEngine,Runnable {
    private MoveDirection[] directions;
    private final IWorldMap myMap;
    private final App observer;
    private final int moveDelay;

    private final Vector2d[] positions;//tylko raz z tego pola korzystamy
    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app, int moveDelay) {
        this.myMap = map;
        this.positions = positions;
        this.observer = app;
        this.moveDelay = moveDelay;

        Arrays.stream(positions).forEach(position-> {
            Animal animal = new Animal(this.myMap, position);
            this.myMap.place(animal);//jak zwierze najdzie na inne, to nie zostanie dodane do mapy
        });
        //System.out.println(myMap.toString());
    }
    public void setDirections(String[] args){
        this.directions = new OptionsParser().parse(args);
    }

    @Override
    public void run() {
        Platform.runLater(this.observer::updateMap);
        try{
            Thread.sleep(moveDelay);
            int animalCounter = this.myMap.getAnimals().size();
            int index = 0;
            List<Animal> listAnimals = new ArrayList<Animal>(this.myMap.getAnimals().values());
            for(MoveDirection dir : directions){
                Animal animal = listAnimals.get(index);
                animal.move(dir);
                index = (index + 1) % animalCounter;
                //System.out.println(this.myMap);
                Platform.runLater(this.observer::updateMap);
                Thread.sleep(moveDelay);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e + "Przerwano symulację");
        }

    }

}
