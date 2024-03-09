public class App {
    public static void main(String[] args) throws Exception {
        Scratchcard myScratchcard = new Scratchcard(Utils.getFileLines("./files/puzzle_input.txt"));
        System.out.println(myScratchcard.getWinningNumbersSum());
    }
}
