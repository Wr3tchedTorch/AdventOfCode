public class App {
    public static void main(String[] args) {
        // 251275981
        CamelCards myCamelCards = new CamelCards("./files/puzzle_input.txt");
        System.out.println(myCamelCards.getTotalWinnings());
    }
}
