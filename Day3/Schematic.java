import java.util.ArrayList;
import java.util.Map;

public class Schematic {
    ArrayList<String> schematicMapLines = new ArrayList<>();
    
    Schematic(ArrayList<String> schematicMap) {
        schematicMapLines = schematicMap;
    }

    private boolean isSymbol(char letter) {
        return (!Character.isDigit(letter) && !Character.isLetter(letter) && letter != '.');
    }

    private ArrayList<Integer> getNumbers() {
        ArrayList<Integer> schematicNumbers = new ArrayList<>();

        for (String line : schematicMapLines) {
            String numberFound = "";
            boolean gettingNewNumber = false;
            for (char letter : line.toCharArray()) {                
                if (Character.isDigit(letter)) {
                    gettingNewNumber = true;
                }                
                
                if (gettingNewNumber && !Character.isDigit(letter)) {
                    schematicNumbers.add(Integer.parseInt(numberFound));
                    numberFound = "";
                    gettingNewNumber = false;
                }

                if (gettingNewNumber) {
                    numberFound += letter;
                }
            }
        }

        return schematicNumbers;
    }

    private int[] getNumberPosition(int number) {
        int[] numberPositions = new int[2];
        
        String completeText = Utils.getFullTextFromLinesList(schematicMapLines);
        String textToSearch = completeText.replaceAll("[^a-zA-Z0-9]"," ");
        
        int startingPos = textToSearch.indexOf(Integer.toString(number) + " ");
        if (startingPos == -1) { startingPos = textToSearch.indexOf(" " + Integer.toString(number)); }
        int endingPos = startingPos + Integer.toString(number).length()-1;

        numberPositions[0] = startingPos;
        numberPositions[1] = endingPos;

        return numberPositions;
    }

    public float getPartNumbersProduct() {
        getNumberPosition(755);
        return 0f;
    }
}