package agh.ics.oop;

import java.util.*;

public class Animal extends AbstractWorldMapElement{

    private IWorldMap myMap;
    protected final List<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection orientation =  MapDirection.NORTH;
    //w konstruktorach przypisujemy zwierze do danej mapy, ale nie umieszczamy zwierzecia na tej mapie!!
    public Animal(IWorldMap map, Vector2d initialPosition){
            super(initialPosition);
            this.myMap = map;

    }
    public Animal(IWorldMap map){
        super(new Vector2d(2,2));
        this.myMap = map;
    }

    public Animal(){
        super(new Vector2d(2,2));
        this.myMap = new RectangularMap(4, 4);
    }

    public String toString(){
        return orientation.toString();
    }
    public String extendedToString(){
        return position.toString() + " " + orientation.toString();
    }


    public void move(MoveDirection direction0){
        switch(direction0){
            case RIGHT:
                orientation = orientation.next();
                break;
            case LEFT:
                orientation = orientation.previous();
                break;
            case FORWARD:
                Vector2d newVectorek0 = position.add(orientation.toUnitVector());
                if(myMap instanceof GrassField && updatePositions(newVectorek0)) break;//sprawdzanie czy tam jest trawa
                else if(myMap.canMoveTo(newVectorek0)){
                    positionChanged(position, newVectorek0);
                    this.position = newVectorek0;
                }
                break;
            case BACKWARD:
                Vector2d newVectorek1 = position.add(orientation.next().next().toUnitVector());
                if(myMap instanceof GrassField && updatePositions(newVectorek1)) break;
                else if(myMap.canMoveTo(newVectorek1)){
                    positionChanged(position, newVectorek1);
                    this.position = newVectorek1;
                }
                break;
            default:
                System.out.println("Nieznany kierunek");
                }
        }



    public MapDirection getDirection() {
        return orientation;
    }
    public boolean updatePositions(Vector2d newVectorek0){
        GrassField mapa = (GrassField) this.myMap;
        if(mapa.objectAt(newVectorek0) instanceof Grass){

            //nowe polozenie trawy:
            Vector2d newVec = mapa.uniqPosVector(new Vector2d(mapa.boundary,mapa.boundary));
            Vector2d oldVec = ((Grass) mapa.objectAt(newVectorek0)).getPosition();
            ((Grass) mapa.objectAt(newVectorek0)).setPosition(newVec);
            mapa.positionChangedGrass(oldVec,newVec);

            //nowe polozenie zwierzaka
            mapa.positionChanged(position, newVectorek0);
            this.position = newVectorek0;

            return true;
        }
        return false;
    }


    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    protected void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : this.observers){ //dla wszystkich obserwowanych map trzeba zrobic zmiane!
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}

