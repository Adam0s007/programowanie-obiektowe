package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection direction =  MapDirection.NORTH;

    public String toString(){
        return position.toString() + " " + direction.toString();
    }
    public boolean isAt(Vector2d position1){
        return this.position.x == position1.x && this.position.y == position1.y;
    }
    public void move(MoveDirection direction0){
        switch(direction0){
            case RIGHT:
                direction = direction.next();
                break;
            case LEFT:
                direction = direction.previous();
                break;
            case FORWARD:
                Vector2d newVectorek0 = position.add(direction.toUnitVector());
                if(newVectorek0.x < 0 || newVectorek0.x >= 5 || newVectorek0.y < 0 || newVectorek0.y >= 5){
                    break;
                }
                position = newVectorek0;
                break;
            case BACKWARD:
                Vector2d newVectorek1 = position.add(direction.next().next().toUnitVector());
                if(newVectorek1.x < 0 || newVectorek1.x >= 5 || newVectorek1.y < 0 || newVectorek1.y >= 5){
                    break;
                }
                position = newVectorek1;
                break;
            default:
                System.out.println("Nieznany kierunek");
                }


        }


    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return direction;
    }
}

