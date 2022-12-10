package agh.ics.oop;

public interface IMapElement {
    String toString();
    // return a representation of an object
    Vector2d getPosition();
    // return current postion of an object

    String getLinkToImage();
    //get link to image
    boolean isAt(Vector2d position);
}
