import static org.junit.Assert.*;
import org.junit.*;


public class MapNetworkTest {
    @Test
    public void testFirstExempleShouldReturnTwo() {
        MapNetwork myMap = new MapNetwork(Utils.getFileLines("./files/first_example.txt"));
        assertEquals(2, myMap.getStepsNumber());
    }

    @Test
    public void testSecondExempleShouldReturnSix() {
        MapNetwork myMap = new MapNetwork(Utils.getFileLines("./files/second_example.txt"));
        assertEquals(6, myMap.getStepsNumber());
    }

    @Test
    public void testShouldReturn16343() {
        MapNetwork myMap = new MapNetwork(Utils.getFileLines("./files/third_test.txt"));
        assertEquals(16343, myMap.getStepsNumber());
    }
}
