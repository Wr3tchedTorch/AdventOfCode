import java.util.ArrayList;

public class BoatRace {
    private ArrayList<String> fileLines = new ArrayList<>();

    BoatRace(String filePath) {
        fileLines = Utils.getFileLines(filePath);
    }

    public int getWaysToWinProduct() {
        int totalProduct = 1;    
        
        String firstLine = fileLines.get(0);
        String secondLine = fileLines.get(1);
        String firstLineNumbers = firstLine.substring(5, firstLine.length()).trim().replaceAll("( )+", " ");
        String secondLineNumbers = secondLine.substring(9, secondLine.length()).trim().replaceAll("( )+", " ");

        ArrayList<Integer> timeToTravelList = Utils.convertStringToIntegerArray(firstLineNumbers);
        ArrayList<Integer> recordDistanceList = Utils.convertStringToIntegerArray(secondLineNumbers);
        int timeLeftToTravel = 0;
        int distanceTravelled = 0;

        int waysToWinCounter = 0;
        int i = 0;
        for (Integer timeToTravel : timeToTravelList) {
            int recordDistance = recordDistanceList.get(i);

            for (int j = 1; j < timeToTravel; j++) {
                timeLeftToTravel = timeToTravel - j;
                distanceTravelled = timeLeftToTravel * j;

                if (distanceTravelled > recordDistance) {
                    waysToWinCounter++;
                }

            }

            totalProduct *= waysToWinCounter;
            waysToWinCounter = 0;

            i++;
        }

        return totalProduct;
    }
}