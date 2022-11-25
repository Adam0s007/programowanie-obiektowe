package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position = new Vector2d(2,2);
    protected final List<IPositionChangeObserver> observers = new ArrayList<>();
    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }
    public void setPosition(Vector2d position){
        this.position = position;
    }

    public boolean isAt(Vector2d position1){
        return position.equals(position1);
    }


}