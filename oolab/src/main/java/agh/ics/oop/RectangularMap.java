package agh.ics.oop;

import java.util.*;
import static java.lang.Math.*;

public class RectangularMap implements IWorldMap{

private final Vector2d bottomLeft = new Vector2d(0,0);
private Vector2d topRight = new Vector2d(4,4);//domyslnie
private MapVisualizer displayer = new MapVisualizer(this);
private LinkedList<Animal> animals = new LinkedList<>();

public LinkedList<Animal> getAnimals() {
    return animals;
}

public RectangularMap(int width,int height){
    if(width >0 && height >0) this.topRight = new Vector2d(abs(width),abs(height)); // nigdy nie zakladamy ze dane będą zawsze dodatnie ;)
}
public RectangularMap(){}; //jesli zostawiamy domyslny rozmiar

public String toString(){
    return displayer.draw(this.getBottomLeft(),this.getTopRight());
}

@Override
public boolean canMoveTo(Vector2d newPos){
    //jesli na danej pozycji cos jest, zwroc false natychmiast, w przeciwnym razie zwroc true/false zaleznie czy wychodzimy poza mape
    return this.isOccupied(newPos) ? false : newPos.follows(this.getBottomLeft()) && newPos.precedes(this.getTopRight());
}

@Override
public boolean place(Animal animal) {
    if(!this.canMoveTo(animal.getPosition())) return false;
    animals.add(animal);
    return true;
}

//wykorzystanie metody nadpisanej equals w Vector2D
@Override
public boolean isOccupied(Vector2d position) {
    return animals.stream().anyMatch((animal -> animal.isAt(position)));
}

@Override
public Object objectAt(Vector2d position) {                                      //zwroci obiekt lub null (jesli nie znalezione)
    return animals.stream().filter(animal -> animal.isAt(position)).findFirst().orElse(null);
}

public Vector2d getBottomLeft() {
    return bottomLeft;
}

public Vector2d getTopRight() {
    return topRight;
}


}
