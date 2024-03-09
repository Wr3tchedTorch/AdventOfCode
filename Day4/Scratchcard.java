import java.util.ArrayList;

public class Scratchcard {
    private ArrayList<String> cardsList = new ArrayList<>();

    Scratchcard(ArrayList<String> cards) {
        this.cardsList = cards;
    }

    public int getWinningNumbersSum() {
        int sum = 0;

        for (String card : cardsList) {
            card = card.split(":")[1];
    
            String[] myNumbers = card.split("\\|")[1].trim().replaceAll(" +", " ").split(" ");
            String winningNumbers = card.split("\\|")[0];
    
            boolean first_card = true;
            int card_score = 0;
            for (String numberBeingChecked : myNumbers) {
                if (winningNumbers.contains(" " + numberBeingChecked + " ")) {
                    if (first_card) {
                        first_card = false;
                        card_score++;
                    } else
                        card_score *= 2;
                }
            }                
            sum += card_score;
        }
        return sum;
    }
}
