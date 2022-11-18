package agh.ics.oop;

public class Grass {
    Vector2d position;
    public Grass(Vector2d position){
        this.position = position;
    };
    Vector2d getPosition(){
        return this.position;
    }
    public void setPosition(Vector2d position){
        this.position = position;
    }

    public String toString(){
        return "*";
    }

    public boolean isAt(Vector2d position1){
        return position.equals(position1);
    }
}
