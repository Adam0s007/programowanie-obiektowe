package agh.ics.oop;
import java.util.*;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        List<MoveDirection> Move_direction_list = new ArrayList<MoveDirection>();
        for(String arg: args){
            if(arg.equals("b") || arg.equals("backward")){
                Move_direction_list.add(MoveDirection.BACKWARD);
            }
            else if(arg.equals("f") || arg.equals("forward")){
                Move_direction_list.add(MoveDirection.FORWARD);
            }
            else if(arg.equals("r") || arg.equals("right")){
                Move_direction_list.add(MoveDirection.RIGHT);
            }
            else if(arg.equals("l") || arg.equals("left")){
                Move_direction_list.add(MoveDirection.LEFT);
            }
        }
        int leng = Move_direction_list.size();
        return Move_direction_list.toArray(new MoveDirection[leng]);
    }
}
