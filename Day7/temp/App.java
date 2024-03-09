package temp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Scanner readTextFile = new Scanner(new File("input.txt"));

            ArrayList<String> cards = new ArrayList<>();
            ArrayList<String> cardsBid = new ArrayList<>();

            while (readTextFile.hasNextLine()) {
                String line = readTextFile.nextLine();

                cards.add(line.substring(0, 5));
                cardsBid.add(line.substring(5, line.length()).trim());
            }

            ArrayList<String> cardTypes = new ArrayList<>();

            App app = new App();

            cardTypes.add(app.getCardType(cards.get(0)));

            System.out.println("Card type: " + cardTypes.get(0));

        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public String getCardType(String _card) {
        String cardType = "";

        String cardWithoutAlreadyCheckedLetters = _card;
        String cardWithoutLetter = _card;

        int numberOfMatches = 0;
        int index = 0;

        ArrayList<Integer> cardLetterOccurrences = new ArrayList<>();

        while (cardWithoutAlreadyCheckedLetters.length() > 0) {
            System.out.println("Index: " + index + " tamanho da array " + cardWithoutAlreadyCheckedLetters.length());
            char letter = cardWithoutAlreadyCheckedLetters.toCharArray()[index];
            

            index++;

            cardWithoutAlreadyCheckedLetters = cardWithoutAlreadyCheckedLetters.replace(Character.toString(letter), "");
            cardWithoutLetter = _card.replace(Character.toString(letter), "");
            int letterNumberOfOccurences = _card.length() - cardWithoutLetter.length();

            System.out.println("Letra " + letter + " ocorrencias: " + cardWithoutAlreadyCheckedLetters);

            if (letterNumberOfOccurences >= 2) {
                cardLetterOccurrences.add(letterNumberOfOccurences);
                numberOfMatches++;
            }

            if (letterNumberOfOccurences == 5) {
                cardType = "Five of a kind";
                break;
            } else if (letterNumberOfOccurences == 4) {
                cardType = "Four of a kind";
                break;
            }

        }

        if (numberOfMatches == 2) {
            // Can be either: Full house: xxxyy; Two pair: xxyyz;

            for (Integer letterOccurrences : cardLetterOccurrences) {
                if (letterOccurrences == 3) {
                    cardType = "full-house";
                    break;
                }
                if (letterOccurrences == 2) {
                    cardType = "two-pair";
                }
            }
        }

        System.out.println("Number of matches found in the card: " + numberOfMatches);
        return cardType;
    }
}