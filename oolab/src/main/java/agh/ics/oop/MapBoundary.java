package agh.ics.oop;

import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private final TreeSet<Vector2d> xAxis = new TreeSet<>(new CmpX());
    private final TreeSet<Vector2d> yAxis = new TreeSet<>(new CmpY());

    protected void addElementToMap(Vector2d vector){
        this.xAxis.add(vector);
        this.yAxis.add(vector);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.xAxis.remove(oldPosition);
        this.yAxis.remove(oldPosition);
        addElementToMap(newPosition);
    }

    public Vector2d lowerLeftChecker(){
        Vector2d vectorX = this.xAxis.first();
        Vector2d vectorY = this.yAxis.first();
        Vector2d start = vectorX.lowerLeft(vectorY);
        return start;
    }

    public Vector2d upperRightChecker(){
        Vector2d vectorX = this.xAxis.last();
        Vector2d vectorY = this.yAxis.last();
        Vector2d end = vectorX.upperRight(vectorY);
        return end;
    }
}
