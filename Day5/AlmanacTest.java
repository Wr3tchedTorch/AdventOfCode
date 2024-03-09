import org.junit.*;
import static org.junit.Assert.*;

public class AlmanacTest {
    @Test
    public void testFirstExample() {
        Almanac subject = new Almanac("./files/first_example.txt");
        assertEquals(35l, subject.getLowestLocationNum());
    }

    @Test
    public void testPuzzleInput() {
        Almanac subject = new Almanac("./files/puzzle_input.txt");
        assertEquals(227653707l, subject.getLowestLocationNum());
    }
}
