import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Schematic {
    String schematicInput = "";

    Schematic(String schematicInput) {
        this.schematicInput = schematicInput;
    }

    private boolean hasSymbol(String text) {
        boolean hasSymbol = false;

        for (char letter : text.toCharArray()) {
            hasSymbol = (!Character.isDigit(letter) && !Character.isLetter(letter) && !Character.isWhitespace(letter) && letter != '.');

            if (hasSymbol) break;
        }

        return hasSymbol;
    }    

    private ArrayList<String> convertTextIntoLinesList(String textToConvert) {
        ArrayList<String> linesList = new ArrayList<>();

        Scanner readText = new Scanner(textToConvert);
        

        while (readText.hasNextLine()) {
            linesList.add(readText.nextLine());
        }
        
        readText.close();

        return linesList;
    }

    private boolean isPartNumber() {

        return false;
    }    

    private String[] getAdjacentRows(int _numberStartingPos, int _numberFinalPos, int _indexOfTheLineBeingChecked, ArrayList<String> _linesList) {
        ArrayList<String> adjacentRows = new ArrayList<>();

        String lineBeingChecked = _linesList.get(_indexOfTheLineBeingChecked);

        adjacentRows.add("");
        adjacentRows.add("");
        adjacentRows.add("");
        adjacentRows.add("");

        int leftColumnOffSet = 0;

        if (_numberStartingPos-1 >= 0) {
            adjacentRows.set(1, Character.toString(lineBeingChecked.charAt(_numberStartingPos-1)));
            leftColumnOffSet = 1;
        }
        
        int rightColumnOffSet = 1;
        
        if (_numberFinalPos+2 < lineBeingChecked.length()-1) {
            adjacentRows.set(3, Character.toString(lineBeingChecked.charAt(_numberFinalPos+1)));
            rightColumnOffSet = 2;
        }
        
        boolean hasTopline = _indexOfTheLineBeingChecked - 1 > 0;
        boolean hasBottomline = _indexOfTheLineBeingChecked + 1 < _linesList.size();

        String topLine = "";
        String bottomLine = "";

        if (hasTopline) { topLine = _linesList.get(_indexOfTheLineBeingChecked-1); }
        if (hasBottomline) { bottomLine = _linesList.get(_indexOfTheLineBeingChecked+1); }

        if (topLine != "") { adjacentRows.set(0, topLine.substring(_numberStartingPos-leftColumnOffSet, _numberFinalPos+rightColumnOffSet)); }
        if (bottomLine != "") { adjacentRows.set(2, bottomLine.substring(_numberStartingPos-leftColumnOffSet, _numberFinalPos+rightColumnOffSet)); }

        return adjacentRows.toArray(new String[0]);        
    }

    public ArrayList<Integer> getPartNumbers() {        
        ArrayList<String> linesList = this.convertTextIntoLinesList(this.schematicInput);
        ArrayList<Integer> partNumbers = new ArrayList<>();

        int i = 0;

        
        
        for (String lineBeingChecked : linesList) {            
            int numberStartingPos = -1;
            int numberFinalPos = -1;
    
            String numberFound = "";
    
            boolean gettingNewNumber = false;
            boolean isNumber = false;
    
            int j = 0;
            
            for (char letter : lineBeingChecked.toCharArray()) {                    
                isNumber = Character.isDigit(letter);

                if (isNumber) {
                    if (!gettingNewNumber) { numberStartingPos = j; }

                    gettingNewNumber = true;
                    numberFound += letter;
                }

                if (gettingNewNumber && (!isNumber || j == lineBeingChecked.length()-1)) {
                        numberFinalPos = j-1;
                        
                        // Checking if the new found number is a part number
                        
                        String[] adjacenteRows = getAdjacentRows(numberStartingPos, numberFinalPos, i, linesList);

                        String topRow = adjacenteRows[0];
                        String rightLetterValue = adjacenteRows[1];
                        String bottomRow = adjacenteRows[2];
                        String leftLetterValue = adjacenteRows[3];

                        
                        boolean isPartNumber = hasSymbol(leftLetterValue) || hasSymbol(topRow) || hasSymbol(bottomRow) || hasSymbol(rightLetterValue);
                        
                        if (isPartNumber) {
                            partNumbers.add(Integer.parseInt(numberFound));                            
                        }

                        numberFound = "";
                        gettingNewNumber = false;
                }
                
                
                j++;
            }
        }
        

        return partNumbers;
    }
    
}
