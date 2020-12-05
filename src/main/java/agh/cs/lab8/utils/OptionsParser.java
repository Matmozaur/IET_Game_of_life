package agh.cs.lab8.utils;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public static MoveDirection[] parse(String[] args) {
        List<MoveDirection> moves = new ArrayList<>();
        for (String argument : args) {
            MoveDirection move = parse(argument);
            if (move != null) {
                moves.add(move);
            }
        }
        return(moves.toArray(new MoveDirection[0]));
    }
    private static MoveDirection parse(String argument) {
        return switch (argument) {
            case "f", "forward" -> (MoveDirection.FORWARD);
            case "b", "backward" -> (MoveDirection.BACKWARD);
            case "r", "right" -> (MoveDirection.RIGHT);
            case "l", "left" -> (MoveDirection.LEFT);
            default -> throw new IllegalArgumentException(argument + " is not legal move specification");
        };
    }

}
