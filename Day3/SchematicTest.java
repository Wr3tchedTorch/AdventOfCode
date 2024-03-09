import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class SchematicTest {
    @Test
    public void testOneLineMap() {
        ArrayList<String> myMap = new ArrayList<String>();
        myMap.add("467*.114/.");

        Schematic mySchematic = new Schematic(myMap);
        assertEquals(581, mySchematic.getPartNumbersSum());
    }

    @Test
    public void testMultipleLineMap() {
        Schematic mySchematic = new Schematic(Utils.getFileLines("./files/first_example.txt"));
        assertEquals(4361, mySchematic.getPartNumbersSum());
    }

    @Test
    public void testMainPuzzleMap() {
        Schematic mySchematic = new Schematic(Utils.getFileLines("./files/puzzle_input.txt"));
        assertEquals(525911, mySchematic.getPartNumbersSum());
    }
}