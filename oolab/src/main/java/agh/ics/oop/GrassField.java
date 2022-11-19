package agh.ics.oop;
import java.util.*;
import java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    int n;

    private final ArrayList<Grass> grasses = new ArrayList<>();
    public GrassField(int n){
        super(new Vector2d(0,0),(int)(Math.sqrt(n*10)));//nie wplynie na wielkosc mapy w klasie nadrzednej!
        this.n = n;
        super.boundary = (int)(Math.sqrt(n*10));
        //this.topRight = new Vector2d(this.boundary,this.boundary);
        this.createGrasses(this.n);


        grasses.stream().forEach(grass->{
            System.out.println(grass.getPosition());
        });
    }

    public void createGrasses(int n){ //definiuje ile mozna stworzyc nowych trawek i randomowo je umeiszcza tam gdzie nie ma obiektow
        for(int i = 0; i < n; i++){
            Vector2d newVec = uniqPosVector(new Vector2d(super.boundary,super.boundary));
            //update granicy!
            super.updateTotalBoundary(newVec);
            grasses.add(new Grass(newVec));
        }
    }


    public boolean isOccupied(Vector2d position) {
        if(super.isOccupied(position))return true;//jesli tam jest zwierzę to zwroc true
        return grasses.stream().anyMatch((grass -> grass.isAt(position))); // jesli tam jest roslina zwroc true
        //zwroc false w przeciwnym wypadku
    }


    public Object objectAt(Vector2d position){                        //zwroci obiekt lub null (jesli nie znalezione)
        return (super.objectAt(position) != null) ?//najpierw sprawdzi czy tam jest zwierze
                super.objectAt(position) : // po pierwsze jesli jest tam zwierzę to zwroci to zwierze, jesli nie to trawe!
                grasses.stream().filter(grass -> grass.isAt(position)).findFirst().orElse(null);
    }




}
