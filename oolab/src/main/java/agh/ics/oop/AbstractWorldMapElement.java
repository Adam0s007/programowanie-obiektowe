package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position = new Vector2d(2,2);

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

    public String getLinkToImage(){
        String result;
        switch(this.toString()){
            case ("N") -> result = "oolab/src/main/resources/up.png";
            case ("E") -> result = "oolab/src/main/resources/right.png";
            case ("S") -> result = "oolab/src/main/resources/down.png";
            case ("W") -> result = "oolab/src/main/resources/left.png";
            case ("*") -> result = "oolab/src/main/resources/grass.png";
            default -> result = null;
        }
        return result;
    }
}