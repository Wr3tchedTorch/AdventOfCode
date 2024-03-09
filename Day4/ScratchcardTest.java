import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ScratchcardTest {
    @Test 
    public void testOneSingleCard() {
        ArrayList<String> myCards = new ArrayList<>();
        myCards.add("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");

        Scratchcard myScratchcard = new Scratchcard(myCards);
        assertEquals(8, myScratchcard.getWinningNumbersSum());
    }

    @Test 
    public void testOneSingleCardWithoutWinningNumbers() {
        ArrayList<String> myCards = new ArrayList<>();
        myCards.add("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");

        Scratchcard myScratchcard = new Scratchcard(myCards);
        assertEquals(0, myScratchcard.getWinningNumbersSum());
    }
    
    @Test
    public void testFirstExample() {
        Scratchcard myScratchcard = new Scratchcard(Utils.getFileLines("./files/first_example.txt"));
        assertEquals(13, myScratchcard.getWinningNumbersSum());
    }

    @Test
    public void testPuzzleInput() {
        Scratchcard myScratchcard = new Scratchcard(Utils.getFileLines("./files/puzzle_input.txt"));
        assertEquals(23678, myScratchcard.getWinningNumbersSum());
    }
}
