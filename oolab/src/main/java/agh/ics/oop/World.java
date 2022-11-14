package agh.ics.oop;
import java.util.Arrays;
public class World {
    public static void run(Direction[] directions){
        for(Direction direction: directions ) {
            switch(direction) {
                case f:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case r:
                    System.out.println("Zwierzak skreca w prawo");
                    break;
                case b:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case l:
                    System.out.println("Zwierzak skreca w lewo");
                    break;
                default:
                    System.out.println("Nieznany kierunek");
            }
        }
    }

    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(12, 16);
        Vector2d[] positions = {new Vector2d(12, 0),new Vector2d(0, 0),new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(1, 1), new Vector2d(4, 4), new Vector2d(7, 8), new Vector2d(9, 10)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        //"f" "b" "r" "l" "f" "f" "r" "r" "f" "f" "f" "f" "f" "f" "f" "f"
    }
}