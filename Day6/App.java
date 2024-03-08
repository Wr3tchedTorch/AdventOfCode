import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try{            
            Scanner readTextFile = new Scanner(new File("input.txt"));            

            String firstLine = readTextFile.nextLine();
            String secondLine = readTextFile.nextLine();

            readTextFile.close();

            String firstLineNumbers = firstLine.substring(5, firstLine.length()).trim().replaceAll("( )+", " ");

            String secondLineNumbers = secondLine.substring(9, secondLine.length()).trim().replaceAll("( )+", " ");

            App app = new App();

            ArrayList<Integer> timeToTravelList = app.stringToIntegerArray(firstLineNumbers);
            ArrayList<Integer> recordDistanceList = app.stringToIntegerArray(secondLineNumbers);

            int timeLeftToTravel = 0;
            int distanceTravelled = 0;

            int possibleWaysToWinCount = 0;
            int productOfAllPossibleWaysToWinCounts = 1;

            int i = 0;
            
            for (Integer timeToTravel : timeToTravelList) {
                int recordDistance = recordDistanceList.get(i);
                
                for (int j = 1; j < timeToTravel; j++) {
                    timeLeftToTravel = timeToTravel-j;
                    distanceTravelled = timeLeftToTravel * j;

                    if (distanceTravelled > recordDistance) { possibleWaysToWinCount++; }
                    
                }
                
                productOfAllPossibleWaysToWinCounts *= possibleWaysToWinCount;
                possibleWaysToWinCount = 0;
                
                i++;                
            }
            
            System.out.println("Product of all possible ways to win on each race: " + productOfAllPossibleWaysToWinCounts);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> stringToIntegerArray(String _stringWithNumbers) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String number : _stringWithNumbers.split(" ")) {
            numbers.add(Integer.parseInt(number));
        }

        return numbers;
    }

}