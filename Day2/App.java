// --- Day 2: Cube Conundrum ---
// https://adventofcode.com/2023/day/2

public class App {
    public static void main(String[] args) {
        Game myGame = new Game(Utils.getFileLines("./files/first_example.txt"));
        System.out.println(myGame.getIdsSum());
    }
}
