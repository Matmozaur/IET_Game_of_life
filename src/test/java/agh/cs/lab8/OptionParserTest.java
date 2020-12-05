package agh.cs.lab8;
import static org.junit.Assert.assertArrayEquals;

import agh.cs.lab8.utils.MoveDirection;
import agh.cs.lab8.utils.OptionsParser;
import org.junit.Test;

public class OptionParserTest {
    @Test
    public void nextForAllCases() {
        assertArrayEquals("should return list [left, right, forward, forward]",
                new MoveDirection[] {MoveDirection.LEFT,MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD},
                OptionsParser.parse(new String[] {"l", "r", "forward", "f"}));
    }
}
