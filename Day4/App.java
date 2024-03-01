import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {                
        try {
            Scanner read_file = new Scanner(new File("input.txt"));

            int total_score = 0;

            while (read_file.hasNextLine()) {
                String line = read_file.nextLine();
                line = line.split(":")[1];

                String[] cards = line.split("\\|")[1].trim().replaceAll(" +", " ").split(" ");
                String winning_cards = line.split("\\|")[0];

                int card_score = 0;
                boolean first_card = true;

                
                // ArrayList<String> winning_numbers = new ArrayList<>();

                for (String numberBeingChecked : cards) {
                    if (winning_cards.contains(" " + numberBeingChecked + " ")) {
                        if (first_card) { first_card = false; card_score++; }
                        else card_score *= 2;
                        // winning_numbers.add(numberBeingChecked);
                    }
                }

                // System.out.print("Card " + Integer.toString(lineCount) + " has " + Integer.toString(card_score) + " winning numbers \n\tThey are: ");

                total_score += card_score;
            }

            System.out.println("All your cards combined are worth " + total_score);
            
            read_file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
