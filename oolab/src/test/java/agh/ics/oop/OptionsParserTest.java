package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
class OptionsParserTest {

    @Test
    void parse() {
        String[] options0 = {"a","backward","b","f","forward","cos","b","r"};

        OptionsParser pars = new OptionsParser();
        MoveDirection[] parsed0 = pars.parse(options0);

        MoveDirection  [] dir0 = {
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT};
        assertTrue(Arrays.equals(dir0,parsed0));


        String[] options1 = {"a","c"};
        MoveDirection[] parsed1 = pars.parse(options1);
        MoveDirection  [] dir1 = {};

        assertTrue(Arrays.equals(dir1,parsed1));
    }
}