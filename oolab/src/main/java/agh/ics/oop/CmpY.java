package agh.ics.oop;

import java.util.Comparator;

public class CmpY implements Comparator<Vector2d> {

    public int compare(Vector2d vector1, Vector2d vector2) {
        if(vector1.y < vector2.y || (vector1.y == vector2.y && vector1.x < vector2.x)){
            return -1;
        }
        if(vector1.equals(vector2)){
            return 0;
        }
        return 1;
    }
}