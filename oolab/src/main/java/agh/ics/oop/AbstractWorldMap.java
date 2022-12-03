package agh.ics.oop;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver  {
    protected int boundary;
    protected HashMap<Vector2d,Animal> animals = new HashMap<Vector2d,Animal>();
    //zwierzeta mogą poruszac sie po obszarze definiowanym przez bottomLeft i topRight!
    protected Vector2d bottomLeft = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
    protected Vector2d topRight = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);

    //mapa bedzie wyswietlana zgodnie z wszystkimi elementami zawierajacymi sie na mapie


    private MapVisualizer displayer = new MapVisualizer(this);

    protected Random randomizer = ThreadLocalRandom.current();
    protected MapBoundary mapBound = new MapBoundary();

    public Vector2d getLowerLeft(){
        return mapBound.lowerLeftChecker();
    }

    public Vector2d getUpperRight(){
        return mapBound.upperRightChecker();
    }

    public AbstractWorldMap(Vector2d topRight,Vector2d bottomLeft,int boundary){
        if(bottomLeft.precedes(topRight)) {
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
        }
            if(boundary > 0){
                this.boundary = boundary;
            }
            //this.updateTotalBoundary(topRight);
    }


    public String toString(){
//        if(this.topRightBoundary == null || this.bottomLeftBoundary == null){
//            return displayer.draw(this.getBottomLeft(),this.getTopRight());
//        }
        return displayer.draw(mapBound.lowerLeftChecker(), mapBound.upperRightChecker());
    }


    public boolean place(Animal animal) {
        if(objectAt(animal.getPosition()) instanceof Grass){
            //nowe polozenie trawy:
            Vector2d newVec = uniqPosVector(new Vector2d(boundary,boundary));
            Vector2d oldVec = ((Grass) objectAt(animal.getPosition())).getPosition();

            ((Grass) objectAt(animal.getPosition())).setPosition(newVec);
            GrassField myMap = (GrassField) this;
            myMap.positionChangedGrass(oldVec,newVec);

            makeMemeberOfMap(animal);
            return true;
        }
        if(!this.canMoveTo(animal.getPosition())){ throw new IllegalArgumentException(animal.getPosition()+ " - Nie mozna postawić zwierzaka na tej pozycji");}
        makeMemeberOfMap(animal);
        return true;
    }
    public boolean canMoveTo(Vector2d newPos){
        //jesli na danej pozycji cos jest, zwroc false natychmiast, w przeciwnym razie zwroc true/false zaleznie czy wychodzimy poza mape
        //przypadek 1:
        //zwierze chce dostac sie na pozycję, na ktorej jest trawa: wtedy zwierze moze tam wejsc, ale dla trawy trzeba znalezc nową pozycję!
        return this.isOccupied(newPos) ? false : newPos.follows(this.getBottomLeft()) && newPos.precedes(this.getTopRight());
    }

    public void makeMemeberOfMap(Animal animal){
        animals.put(animal.getPosition(),animal);
        mapBound.addElementToMap(animal.getPosition());
        animal.addObserver(this); //do zwierzaka dodajemy obserwowaną mapę!!
        animal.addObserver(mapBound);
    }
    //wykorzystanie metody nadpisanej equals w Vector2D

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }


    public Object objectAt(Vector2d position){                        //zwroci obiekt lub null (jesli nie znalezione)
        return animals.get(position);
        //return animals.stream().filter(animal -> animal.isAt(position)).findFirst().orElse(null);
    }


    public Vector2d getBottomLeft() {
        return bottomLeft;
    }

    public Vector2d getTopRight() {
        return topRight;
    }

    public HashMap<Vector2d,Animal> getAnimals() {
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


    abstract public HashMap<Vector2d, Grass> getGrasses();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
        mapBound.positionChanged(oldPosition,newPosition);
    }


}