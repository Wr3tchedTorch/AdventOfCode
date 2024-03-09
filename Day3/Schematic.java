import java.util.ArrayList;

public class Schematic {
    private ArrayList<String> schematicMapLines = new ArrayList<>();
    private ArrayList<Integer> numbersLineIndex = new ArrayList<>();

    private ArrayList<int[]> numbersPosition = new ArrayList<>();
    
    Schematic(ArrayList<String> schematicMap) {
        schematicMapLines = schematicMap;
    }

    private boolean isSymbol(char letter) {
        return (!Character.isDigit(letter) && !Character.isLetter(letter) && letter != '.');
    }

    private ArrayList<Integer> getNumbers() {
        ArrayList<Integer> schematicNumbers = new ArrayList<>();

        int i = 0;
        for (String line : schematicMapLines) {
            int j = 0;
            String numberFound = "";
            boolean gettingNewNumber = false;
            int startingPos = -1;
            int endingPos = -1;

            for (char letter : line.toCharArray()) {
                if (Character.isDigit(letter)) {
                    if (!gettingNewNumber) startingPos=i*10+j;
                    gettingNewNumber = true;
                    numberFound += letter;
                }
                
                if (gettingNewNumber && (!Character.isDigit(letter) || j == line.length()-1)) {
                    endingPos= i*10+j-1;
                    int[] numberPos = {startingPos, endingPos};
                    numbersPosition.add(numberPos);
                    schematicNumbers.add(Integer.parseInt(numberFound));
                    numbersLineIndex.add(i);
                    numberFound = "";
                    gettingNewNumber = false;
                }
                j++;
                
            }
            i++;
        }
        
        return schematicNumbers;
    }

    private ArrayList<Character> getAdjacentLetters(int number, int numberIndex) {
        int lineIndex = numbersLineIndex.get(numberIndex);
        int[] numberPosition = new int[2];
        numberPosition[0] = numbersPosition.get(numberIndex)[0];
        numberPosition[1] = numbersPosition.get(numberIndex)[1];

        int lineLength = schematicMapLines.get(0).length();

        boolean hasTopLine = lineIndex-1 >= 0;
        boolean hasBottomLine = lineIndex+1 <= schematicMapLines.size()-1;
        boolean hasLeftColumn = numberPosition[0]-lineIndex*10 > 0;
        boolean hasRightColumn = numberPosition[1]-lineIndex*10 < lineLength-1;

        String topLine = "";
        String bottomLine = "";

        if (hasTopLine) topLine = schematicMapLines.get(lineIndex-1);
        if (hasBottomLine) bottomLine = schematicMapLines.get(lineIndex+1);
        String numberLine = schematicMapLines.get(lineIndex);

        ArrayList<Character> adjacentLetters = new ArrayList<>();
        if (hasTopLine) {
            for (char letter : topLine.substring(numberPosition[0]-lineIndex*10, numberPosition[1]-lineIndex*10+1).toCharArray()) {
                adjacentLetters.add(letter);
            }
        } 
        if (hasBottomLine) {
            for (char letter : bottomLine.substring(numberPosition[0]-lineIndex*10, numberPosition[1]-lineIndex*10+1).toCharArray()) {
                adjacentLetters.add(letter);
            }
        }
        if (hasLeftColumn) {
            adjacentLetters.add(numberLine.toCharArray()[numberPosition[0]-lineIndex*10-1]);
            if (hasTopLine) adjacentLetters.add(topLine.toCharArray()[numberPosition[0]-lineIndex*10-1]);
            if (hasBottomLine) adjacentLetters.add(bottomLine.toCharArray()[numberPosition[0]-lineIndex*10-1]);
        }
        if (hasRightColumn) {
            adjacentLetters.add(numberLine.toCharArray()[numberPosition[1]-lineIndex*10+1]);
            if (hasTopLine) adjacentLetters.add(topLine.toCharArray()[numberPosition[1]-lineIndex*10+1]);
            if (hasBottomLine) adjacentLetters.add(bottomLine.toCharArray()[numberPosition[1]-lineIndex*10+1]);
        }

        return adjacentLetters;
    }

    public int getPartNumbersSum() {
        int sum = 0;
        int i = 0;
        for (int number : getNumbers()) {
            ArrayList<Character> adjacentLetters = getAdjacentLetters(number, i);
            for (Character letter : adjacentLetters) {
                if (isSymbol(letter)) {
                    sum += number;
                    break;
                }
            }
            i++;
        }

        return sum;
    }
}