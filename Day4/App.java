

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {                
        try {
            Scanner readFile = new Scanner(new File("input.txt"));

            int totalScore = 0;
            
            while (readFile.hasNextLine()) {
                String currentCard = readFile.nextLine();
                currentCard = currentCard.split(":")[1];

                String[] yourCardNumbers = currentCard.split("\\|")[1].trim().replaceAll(" +", " ").split(" ");
                String winningCardNumbers = currentCard.split("\\|")[0];

                int yourCardScore = 1;
                boolean isReadingFirstCard = true;

                for (String numberBeingChecked : yourCardNumbers) {
                    boolean isWinningNumber = winningCardNumbers.contains(" " + numberBeingChecked + " ");

                    if (isWinningNumber && !isReadingFirstCard) {
                            isReadingFirstCard = false; yourCardScore *= 2; 
                    }
                }                

                totalScore += yourCardScore;
            }

            System.out.println("All your cards combined are worth " + totalScore);
            
            readFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
