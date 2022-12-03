package agh.ics.oop;
import java.util.Objects;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x,int y){
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    boolean precedes(Vector2d other){
        if(this.y <= other.y && this.x <= other.x){
            return true;
        }
        return false;
    }

    boolean follows(Vector2d other){
        if(this.y >= other.y && this.x >= other.x){
            return true;
        }
        return false;
    }

    Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x,this.y + other.y);
    }
    Vector2d substract(Vector2d other){
        return new Vector2d(this.x - other.x,this.y - other.y);
    }
    Vector2d upperRight(Vector2d other){
        int newX = this.x;
        int newY = this.y;
        if(other.x > newX){
            newX = other.x;
        }
        if(other.y > newY){
            newY = other.y;
        }
        return new Vector2d(newX,newY);
    }
    Vector2d lowerLeft(Vector2d other){
        int newX = this.x;
        int newY = this.y;
        if(other.x < newX){
            newX = other.x;
        }
        if(other.y < newY){
            newY = other.y;
        }
        return new Vector2d(newX,newY);
    }
    Vector2d opposite(){
        return new Vector2d(this.x * (-1) ,this.y *(-1) );
    }
    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d betterObj = (Vector2d) other;
        if(betterObj.x == this.x && betterObj.y == this.y){
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }


}
