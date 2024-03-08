// Eric Moura

class App {
    public static void main(String[] args) {
        MapNetwork myMap = new MapNetwork(Utils.getFileLines("files/puzzle_input.txt"));
        System.out.println(myMap.getStepsNumber());
    }
}