import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class GameTest {
    @Test
    public void testOnePossibleGame() {
        ArrayList<String> gameRecord = new ArrayList<>();
        gameRecord.add("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

        Game myGame = new Game(gameRecord);
        assertEquals(1, myGame.getIdsSum());
    }

    @Test
    public void testOneImpossibleGame() {
        ArrayList<String> gameRecord = new ArrayList<>();
        gameRecord.add("Game 1: 15 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

        Game myGame = new Game(gameRecord);
        assertEquals(0, myGame.getIdsSum());
    }

    @Test
    public void testTreePossibleGames() {
        Game myGame = new Game(Utils.getFileLines("files/first_example.txt"));
        assertEquals(8, myGame.getIdsSum());
    }

    @Test
    public void testBigListOfGameRecords() {
        Game myGame = new Game(Utils.getFileLines("files/puzzle_input.txt"));
        assertEquals(2265, myGame.getIdsSum());
    }


}
