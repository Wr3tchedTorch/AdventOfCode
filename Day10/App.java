public class App {
    public static void main(String[] args) {
        PipeSketch myPipeSketch = new PipeSketch(Utils.getTextFromFile("./TestFiles/TestSmallSketch.txt"));
        myPipeSketch.getFarthestDistanceInLoop();
    } 
}