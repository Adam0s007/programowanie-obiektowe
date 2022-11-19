package agh.ics.oop;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

abstract class AbstractWorldMap implements IWorldMap {
    protected int boundary;
    protected ArrayList<Animal> animals = new ArrayList<>();
    //zwierzeta mogą poruszac sie po obszarze definiowanym przez bottomLeft i topRight!
    protected Vector2d bottomLeft = new Vector2d(0,0);
    protected Vector2d topRight = new Vector2d(4,4);

    //mapa bedzie wyswietlana zgodnie z wszystkimi elementami zawierajacymi sie na mapie
    protected Vector2d topRightBoundary = null;
    protected Vector2d bottomLeftBoundary = null;

    private MapVisualizer displayer = new MapVisualizer(this);

    protected Random randomizer = ThreadLocalRandom.current();


    public AbstractWorldMap(Vector2d topRight,Vector2d bottomLeft,int boundary){
        if(bottomLeft.precedes(topRight)){
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            if(boundary > 0){
                this.boundary = boundary;
            }
            //this.updateTotalBoundary(topRight);
        }

    }


    public String toString(){
        this.changerOfBoundary();
//        if(this.topRightBoundary == null || this.bottomLeftBoundary == null){
//            return displayer.draw(this.getBottomLeft(),this.getTopRight());
//        }
        return displayer.draw(this.getBottomLeftBoundary(),this.getTopRightBoundary());
    }


    public boolean place(Animal animal) {
        if(objectAt(animal.getPosition()) instanceof Grass){
            //nowe polozenie trawy:
            Vector2d newVec = uniqPosVector(new Vector2d(boundary,boundary));
            ((Grass) objectAt(animal.getPosition())).setPosition(newVec);

            animals.add(animal);
            return true;

        }
        if(!this.canMoveTo(animal.getPosition())){ return false;}
        animals.add(animal);
        return true;
    }
    public boolean canMoveTo(Vector2d newPos){
        //jesli na danej pozycji cos jest, zwroc false natychmiast, w przeciwnym razie zwroc true/false zaleznie czy wychodzimy poza mape
        //przypadek 1:
        //zwierze chce dostac sie na pozycję, na ktorej jest trawa: wtedy zwierze moze tam wejsc, ale dla trawy trzeba znalezc nową pozycję!
        return this.isOccupied(newPos) ? false : newPos.follows(this.getBottomLeft()) && newPos.precedes(this.getTopRight());
    }

    //wykorzystanie metody nadpisanej equals w Vector2D

    public boolean isOccupied(Vector2d position) {
        return animals.stream().anyMatch((animal -> animal.isAt(position)));
    }


    public Object objectAt(Vector2d position){                        //zwroci obiekt lub null (jesli nie znalezione)
        return animals.stream().filter(animal -> animal.isAt(position)).findFirst().orElse(null);
    }
    public void setMovingField(int width,int height){
        if(width != 0 && height != 0){

            this.topRight = new Vector2d(width,height);;
            this.bottomLeft = new Vector2d(0,0);
        }
    }

    public Vector2d getBottomLeft() {
        return bottomLeft;
    }

    public Vector2d getTopRight() {
        return topRight;
    }
    public Vector2d getTopRightBoundary(){
        return this.topRightBoundary;
    }
    public Vector2d getBottomLeftBoundary(){
        return this.bottomLeftBoundary;
    }



    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    protected Vector2d getRandom(Vector2d vector){
        return new Vector2d(
                randomizer.nextInt(vector.x+1),
                randomizer.nextInt(vector.y+1));
    }
    protected Vector2d uniqPosVector(Vector2d vector){ //vector to graniczny vector! topRight naszej granicy
        Vector2d newVec = this.getRandom(vector);
        while(this.objectAt(newVec) != null){newVec = this.getRandom(vector);}
        return newVec;
    }

    public void changerOfBoundary(){ //daje skrajne punkty zawsze  jako krance mapy
        this.topRightBoundary = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
        this.bottomLeftBoundary = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        animals.stream().forEach(animal->{
            if(animal.getPosition().x > this.topRightBoundary.x) this.topRightBoundary.x = animal.getPosition().x;
            if(animal.getPosition().y > this.topRightBoundary.y) this.topRightBoundary.y = animal.getPosition().y;

            if(animal.getPosition().x < this.bottomLeftBoundary.x) this.bottomLeftBoundary.x = animal.getPosition().x;
            if(animal.getPosition().y < this.bottomLeftBoundary.y) this.bottomLeftBoundary.y = animal.getPosition().y;
        });

    }

}