import org.junit.*;
import static org.junit.Assert.*;

public class BoatRaceTest {
    @Test
    public void testFirstExample() {
        BoatRace myRace = new BoatRace("./files/first_example.txt");
        assertEquals(288, myRace.getWaysToWinProduct());
    }

    @Test
    public void testPuzzleInput() {
        BoatRace myRace = new BoatRace("./files/puzzle_input.txt");
        assertEquals(5133600, myRace.getWaysToWinProduct());
    }
}
