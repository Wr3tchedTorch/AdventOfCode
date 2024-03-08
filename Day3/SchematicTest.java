import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class SchematicTest {
    @Test
    public void testOneLineMap() {
        ArrayList<String> myMap = new ArrayList<String>();
        myMap.add("467*.114/.");

        Schematic mySchematic = new Schematic(myMap);
        assertEquals(53.238f, mySchematic.getPartNumbersProduct(), 0);
    }
}