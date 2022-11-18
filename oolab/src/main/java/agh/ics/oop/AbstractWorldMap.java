package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap {
    protected ArrayList<Animal> animals = new ArrayList<>();
    protected final Vector2d bottomLeft = new Vector2d(0,0);
    protected Vector2d topRight = new Vector2d(4,4);//domyslnie!
    private MapVisualizer displayer = new MapVisualizer(this);


    public AbstractWorldMap(Vector2d topRight){
        if(topRight.x != 0 && topRight.y != 0){
            this.topRight = topRight;
        }

    }


    public String toString(){
        return displayer.draw(this.getBottomLeft(),this.getTopRight());
    }


    public boolean canMoveTo(Vector2d newPos){
        //jesli na danej pozycji cos jest, zwroc false natychmiast, w przeciwnym razie zwroc true/false zaleznie czy wychodzimy poza mape
        return this.isOccupied(newPos) ? false : newPos.follows(this.getBottomLeft()) && newPos.precedes(this.getTopRight());
    }


    public boolean place(Animal animal) {
        if(!this.canMoveTo(animal.getPosition())){ return false;}
        animals.add(animal);
        return true;
    }

    //wykorzystanie metody nadpisanej equals w Vector2D

    public boolean isOccupied(Vector2d position) {
        return animals.stream().anyMatch((animal -> animal.isAt(position)));
    }


    public Object objectAt(Vector2d position){                        //zwroci obiekt lub null (jesli nie znalezione)
        return animals.stream().filter(animal -> animal.isAt(position)).findFirst().orElse(null);
    }

    public Vector2d getBottomLeft() {
        return bottomLeft;
    }

    public Vector2d getTopRight() {
        return topRight;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

}