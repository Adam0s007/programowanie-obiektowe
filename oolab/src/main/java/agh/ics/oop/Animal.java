package agh.ics.oop;

import java.util.*;

public class Animal{

    private IWorldMap myMap = new RectangularMap(4, 4);;
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation =  MapDirection.NORTH;
    public Animal(IWorldMap map){
        this.myMap = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
            this.myMap = map;
            this.position = initialPosition;
    }

    public Animal(){
        position = new Vector2d(2,2);
        this.myMap.place(this);
    }
    private Vector2d bottomLeft = new Vector2d(0,0);
    private Vector2d topRight = new Vector2d(4,4);


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

                if(!myMap.canMoveTo(newVectorek0)){
                    break;
                }
                position = newVectorek0;
                break;
            case BACKWARD:
                Vector2d newVectorek1 = position.add(orientation.next().next().toUnitVector());
                if(!myMap.canMoveTo(newVectorek1)){
                    break;
                }
                position = newVectorek1;
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

