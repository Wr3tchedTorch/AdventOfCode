// Day 4 - Objetivo: Encontrar todos os números que fazem parte do motor na esquemática e
// somar todos os números no final. 
// 
// Exemplo de esquemática:
// 
// ..35..633.
// ......#...
// 617*......
// .....+.58.
// ..592.....
// ......755.
// ...$.*....
// .664.598..
// 
// Os números adjacentes a um simbolo são os números que fazem parte do motor, esses números devem ser somados e o resultado deve ser exibido no final do programa

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static boolean hasSymbol(String text) {
        boolean hasSymbol = false;

        for (char letter : text.toCharArray()) {
            hasSymbol = (!Character.isDigit(letter) && !Character.isLetter(letter) && !Character.isWhitespace(letter) && letter != '.');

            if (hasSymbol) break;
        }

        return hasSymbol;
    }

    public static void main(String[] args) {
        File inputTextFile = new File("input.txt");

        try {
            Scanner readTextInput = new Scanner(inputTextFile);

            ArrayList<String> linesList = new ArrayList<>();

            int sumOfPartNumbers = 0;

            while (readTextInput.hasNextLine()) {
                linesList.add(readTextInput.nextLine());
            }
            readTextInput.close();

            int i = 0;            
            
            for (String lineBeingChecked : linesList) {
                boolean hasTopline = i - 1 > 0;
                boolean hasBottomline = i + 1 < linesList.size();

                String topLine = "";
                String bottomLine = "";

                if (hasTopline) { topLine = linesList.get(i-1); }
                if (hasBottomline) { bottomLine = linesList.get(i+1); }

                int numberStartingPos = -1;
                int numberFinalPos = -1;

                String numberFound = "";

                boolean gettingNewNumber = false;
                boolean isNumber = false;

                int j = 0;

                
                for (char letter : lineBeingChecked.toCharArray()) {                    
                    isNumber = Character.isDigit(letter);

                    if (isNumber) {
                        if (!gettingNewNumber) numberStartingPos = j;
                        gettingNewNumber = true;
                        numberFound += letter;
                    }                                       
                    
                    if (gettingNewNumber) {
                        if (!isNumber || j == lineBeingChecked.length()-1) {                            
                            numberFinalPos = j-1;
                            
                            // Checking if the new found number is a part number
                            String topRow = "";
                            String bottomRow = "";
                            
                            String leftLetterValue = "";
                            String rightLetterValue = "";
                            
                            int leftColumnOffSet = 0;
                            if (numberStartingPos-1 >= 0) {
                                leftLetterValue = Character.toString(lineBeingChecked.charAt(numberStartingPos-1));
                                leftColumnOffSet = 1;
                            }
                            
                            int rightColumnOffSet = 1;
                            
                            if (numberFinalPos+2 < lineBeingChecked.length()-1) {
                                rightLetterValue = Character.toString(lineBeingChecked.charAt(numberFinalPos+1));
                                rightColumnOffSet = 2;
                            }
                            
                            
                            if (topLine != "") { topRow = topLine.substring(numberStartingPos-leftColumnOffSet, numberFinalPos+rightColumnOffSet); }
                            if (bottomLine != "") { bottomRow = bottomLine.substring(numberStartingPos-leftColumnOffSet, numberFinalPos+rightColumnOffSet); }
                            
                            boolean isPartNumber = hasSymbol(leftLetterValue) || hasSymbol(topRow) || hasSymbol(bottomRow) || hasSymbol(rightLetterValue);
                            
                            if (isPartNumber) {
                                sumOfPartNumbers += Integer.parseInt(numberFound);
                            }

                            numberFound = "";
                            gettingNewNumber = false;
                        }

                    }
                    
                    j++;
                }
                System.out.println();

                i++;
            }
            System.out.println(sumOfPartNumbers);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
