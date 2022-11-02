package agh.ics.oop;

import java.util.Objects;
import java.util.HashMap;
public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation =  MapDirection.NORTH;


    @Override
    public String toString(){
        return position.toString() + " " + orientation.toString();
    }

    @Override
    public boolean equals(Object other){ //tylko ze wzgledu na wspolrzedne x,y , czyli pozycję!
        if (this == other)
            return true;
        if (!(other instanceof Animal))
            return false;
        Animal betterObj = (Animal) other;
        if(this.position.equals(betterObj.position)){
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(position);
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

                if(!(newVectorek0.follows(new Vector2d(0, 0)) && newVectorek0.precedes(new Vector2d(4, 4)))){
                    break;
                }

                position = newVectorek0;

                break;
            case BACKWARD:
                Vector2d newVectorek1 = position.add(orientation.next().next().toUnitVector());
                if(!(newVectorek1.follows(new Vector2d(0, 0)) && newVectorek1.precedes(new Vector2d(4, 4)))) {
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

