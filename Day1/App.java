// Day 1: Trebuchet?!
// https://adventofcode.com/2023/day/1

public class App {
    public static void main(String[] args) {
        CalibrationDocument myDoc = new CalibrationDocument(Utils.getFileLines("./files/first_test.txt"));
        System.out.println(myDoc.getTotalSum());
    }
}