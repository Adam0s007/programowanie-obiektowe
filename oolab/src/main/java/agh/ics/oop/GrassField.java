package agh.ics.oop;
import java.util.*;
import java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    int n;
    private Random randomizer = ThreadLocalRandom.current();
    private int boundary;
    private final ArrayList<Grass> grasses = new ArrayList<>();
    public GrassField(int n){
        super(new Vector2d(0,0));//nie wplynie na wielkosc mapy w klasie nadrzednej!
        this.n = n;
        this.boundary = (int)(Math.sqrt(this.n*10));
        this.topRight = new Vector2d(this.boundary,this.boundary);

        for(int i = 0; i < n; i++){
            Vector2d newVec = this.getRandom(n);
            while(!this.canMoveTo(newVec)){newVec = this.getRandom(n);}
            grasses.add(new Grass(newVec));
        }
        //display
//        grasses.stream().forEach(grass->{
//            System.out.println(grass.getPosition());
//        });
    }
    private Vector2d getRandom(double n){
        return new Vector2d(
                randomizer.nextInt(boundary+1),
                randomizer.nextInt(boundary+1));

    }

    public boolean isOccupied(Vector2d position) {
        if(super.isOccupied(position))return true;//jesli tam jest zwierzę to zwroc true
        return grasses.stream().anyMatch((grass -> grass.isAt(position))); // jesli tam jest roslina zwroc true
        //zwroc false w przeciwnym wypadku
    }


    public Object objectAt(Vector2d position){                        //zwroci obiekt lub null (jesli nie znalezione)
        return (super.objectAt(position) != null) ?
                super.objectAt(position) :
                grasses.stream().filter(grass -> grass.isAt(position)).findFirst().orElse(null);
    }




}
