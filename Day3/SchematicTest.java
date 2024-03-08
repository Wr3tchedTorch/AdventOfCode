import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class SchematicTest {
    @Test
    public void getPartNumberShouldReturnSingleValueArray() {
        Schematic schema = new Schematic("479#..4");
        ArrayList<Integer> testList = new ArrayList<>();

        testList.add(479);

        assertEquals(testList, schema.getPartNumbers());
    }

    @Test
    public void getPartNumberShouldReturnArrayWithTreeValues() {
        Schematic schema = new Schematic("479#561*23");
        ArrayList<Integer> testList = new ArrayList<>();

        testList.add(479);
        testList.add(561);
        testList.add(23);

        assertEquals(testList, schema.getPartNumbers());
    }

    @Test
    public void getPartNumberShouldReturnArrayWithTwoValuesDiagonalCheck() {
        Schematic schema = new Schematic("467..114..\n...*......\n..35..633.");
        ArrayList<Integer> testList = new ArrayList<>();

        testList.add(467);
        testList.add(35);

        assertEquals(testList, schema.getPartNumbers());
    }
}
