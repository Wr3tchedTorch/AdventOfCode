import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CamelCardsTest {
    @Test
    public void testFirstExample() {
        CamelCards subject = new CamelCards("./files/first_example.txt");
        assertEquals(6440, subject.getTotalWinnings());
    }
    
    @Test
    public void testPuzzleInput() {
        // 251.275.981
        // 251287184
        CamelCards subject = new CamelCards("./files/puzzle_input.txt");
        assertEquals(251287184, subject.getTotalWinnings());
    }
}