import java.util.ArrayList;

public class Game {
    ArrayList<String> gamesRecords = new ArrayList<>();
    ArrayList<String> roundNamesList = new ArrayList<>();
    ArrayList<Integer> roundValuesList = new ArrayList<>();

    Game(ArrayList<String> fileLines) {
        gamesRecords = fileLines;
    }

    private void setRoundValues(String game) {
        String gameWithoutId = game.split(":")[1];

        roundNamesList.clear();
        roundValuesList.clear();

        String[] roundNamesArray = gameWithoutId.replaceAll("[^a-zA-Z' ']", "").trim().replaceAll(" +", " ").split(" ");
        for (String name : roundNamesArray) {
            roundNamesList.add(name);
        }

        String[] roundValuesArray = gameWithoutId.replaceAll("[^0-9' ']", "").trim().replaceAll(" +", " ").split(" ");
        for (String value : roundValuesArray) {
            roundValuesList.add(Integer.parseInt(value));
        }
    }

    private int[] getGamesId() {
        int[] gamesIdList = new int[gamesRecords.size()];

        int i = 0;
        for (String gameRecord : gamesRecords) {
            String gameId = gameRecord.substring(0, gameRecord.indexOf(":"));
            gameId = gameId.replaceAll("[^0-9]", "").trim();

            gamesIdList[i] = Integer.parseInt(gameId);

            i++;
        }

        return gamesIdList;
    }

    private boolean roundIsPossible(String round, int value) {
        boolean isPossible = true;
        if (round.equals("red")) {
            if (value > 12)
                isPossible = false;
        } else if (round.equals("green")) {
            if (value > 13)
                isPossible = false;
        } else if (round.equals("blue")) {
            if (value > 14)
                isPossible = false;
        }
        return isPossible;
    }

    private boolean gameIsPossible(String game) {
        setRoundValues(game);
        boolean gameIsPossible = true;

        int i = 0;
        for (String gameName : roundNamesList) {
            int roundValue = roundValuesList.get(i);
            
            if (!roundIsPossible(gameName, roundValue)) {
                gameIsPossible = false;
                break;
            }
            
            i++;
        }
        return gameIsPossible;
    }

    public int getIdsSum() {
        int idSum = 0;
        int[] gamesIdList = getGamesId();
        
        int j = 0;
        for (String game : gamesRecords) {            
            if (gameIsPossible(game)) {
                idSum += gamesIdList[j];
            }
            
            j++;
        }

        return idSum;
    }
}
