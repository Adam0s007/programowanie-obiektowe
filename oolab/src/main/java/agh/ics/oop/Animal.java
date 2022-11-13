package agh.ics.oop;

import java.util.*;

public class Animal{

    private IWorldMap myMap = new RectangularMap(4, 4);;
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation =  MapDirection.NORTH;
    //w konstruktorach przypisujemy zwierze do danej mapy, ale nie umieszczamy zwierzecia na tej mapie!!
    public Animal(IWorldMap map, Vector2d initialPosition){
            this.myMap = map;
            this.position = initialPosition;
    }
    public Animal(IWorldMap map){
        this.myMap = map;
    }

    public Animal(){

    }

    public String toString(){
        return orientation.toString();
    }
    public String extendedToString(){
        return position.toString() + " " + orientation.toString();
    }



    public boolean isAt(Vector2d position1){
        return position.equals(position1);
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

                if(myMap.canMoveTo(newVectorek0)){
                    position = newVectorek0;
                }
                break;
            case BACKWARD:
                Vector2d newVectorek1 = position.add(orientation.next().next().toUnitVector());
                if(myMap.canMoveTo(newVectorek1)){
                    position = newVectorek1;
                }
                break;
            default:
                System.out.println("Nieznany kierunek");
                }
        }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return orientation;
    }

}

