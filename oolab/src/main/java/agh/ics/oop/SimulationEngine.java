package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Platform;
import java.util.*;


public class SimulationEngine implements IEngine,Runnable {
    private MoveDirection[] directions;
    private final IWorldMap myMap;
    private App observer;
    private int moveDelay;
    private final boolean isAppOpening;
    private final Vector2d[] positions;//tylko raz z tego pola korzystamy
    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app, int moveDelay) {
        this.myMap = map;
        this.positions = positions;
        this.observer = app;
        this.moveDelay = moveDelay;
        this.isAppOpening = true;

        Arrays.stream(positions).forEach(position-> {
            Animal animal = new Animal(this.myMap, position);
            this.myMap.place(animal);//jak zwierze najdzie na inne, to nie zostanie dodane do mapy
        });
        //System.out.println(myMap.toString());
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.myMap = map;
        this.positions = positions;
        this.isAppOpening =false;


        Arrays.stream(positions).forEach(position-> {
            Animal animal = new Animal(this.myMap, position);
            this.myMap.place(animal);//jak zwierze najdzie na inne, to nie zostanie dodane do mapy
        });
        System.out.println(myMap.toString());
    }

    public void setDirections(MoveDirection[] directions){
        this.directions = directions;
    }

    @Override
    public void run() {
        if(this.isAppOpening){
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
                Thread.sleep(2000);
                Platform.exit();

            }catch (InterruptedException e) {
                throw new RuntimeException(e + "Przerwano symulację");
            }
        }else{
            int animalCounter = this.myMap.getAnimals().size();
            int index = 0;
            //HashMap<Vector2d,Animal> animals = this.myMap.getAnimals();
            List<Animal> listAnimals = new ArrayList<Animal>(this.myMap.getAnimals().values());
            //tworzymy
            //JframeOutput framek = new JframeOutput(animals,mapa);
            for(MoveDirection dir : directions){
                Animal animal = listAnimals.get(index);
                animal.move(dir);

                //framek.setPositions(animals,index);
                index = (index + 1) % animalCounter;
                System.out.println(this.myMap);
            }

            //framek.toSleep(2000);
            //framek.discard();
        }


    }

}
