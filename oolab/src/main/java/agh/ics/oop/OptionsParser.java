package agh.ics.oop;
import java.util.*;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        List<MoveDirection> Move_direction_list = new ArrayList<MoveDirection>();
        Arrays.stream(args).forEach(elem -> {

                if (elem.equals("b") || elem.equals("backward")) {
                    Move_direction_list.add(MoveDirection.BACKWARD);
                } else if (elem.equals("f") || elem.equals("forward")) {
                    Move_direction_list.add(MoveDirection.FORWARD);
                } else if (elem.equals("r") || elem.equals("right")) {
                    Move_direction_list.add(MoveDirection.RIGHT);
                } else if (elem.equals("l") || elem.equals("left")) {
                    Move_direction_list.add(MoveDirection.LEFT);
                }else throw new IllegalArgumentException(elem + " is not legal move specification");
        });
        int leng = Move_direction_list.size();
        return Move_direction_list.toArray(new MoveDirection[leng]);
    }
}
