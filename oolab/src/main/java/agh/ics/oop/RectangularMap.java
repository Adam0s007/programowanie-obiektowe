package agh.ics.oop;

import java.util.*;
import static java.lang.Math.*;

public class RectangularMap extends AbstractWorldMap{




public RectangularMap(int width,int height){
    super(new Vector2d(abs(width),abs(height)),new Vector2d(0,0),-1);
         // nigdy nie zakladamy ze dane będą zawsze dodatnie ;)
}
    public HashMap<Vector2d, Grass> getGrasses(){ //dostań roślinność
        return new HashMap<>(); // zwraca pustą liste bo nie zawiera roślinnosci
    }


}
