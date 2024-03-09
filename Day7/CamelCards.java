import java.util.ArrayList;

public class CamelCards {
    private ArrayList<String> cardsList = new ArrayList<>();
    private ArrayList<Long> cardsBid = new ArrayList<>();
    private ArrayList<Integer> cardTypes = new ArrayList<>();

    CamelCards(String filePath) {
        setCardLists(Utils.getFileLines(filePath));
    }

    private void setCardLists(ArrayList<String> fileLines) {
        for (String card : fileLines) {
            cardsList.add(card.substring(0, 5));
            cardsBid.add(Long.parseLong(card.substring(5, card.length()).trim()));
        }

        for (String card : cardsList) {
            cardTypes.add(getCardType(card));
        }
    }

    private int getCardType(String card) {
        int cardType = 0;
        String cardWithoutAlreadyCheckedLetters = card;
        ArrayList<Integer> cardLetterOccurrences = new ArrayList<>();

        while (cardWithoutAlreadyCheckedLetters.length() > 0) {
            char letter = cardWithoutAlreadyCheckedLetters.toCharArray()[0];

            cardWithoutAlreadyCheckedLetters = cardWithoutAlreadyCheckedLetters.replace(Character.toString(letter), "");
            String cardWithoutLetter = card.replace(Character.toString(letter), "");

            int letterNumberOfOccurences = (card.length()-1) - (cardWithoutLetter.length()-1);
            if (letterNumberOfOccurences >= 2) {
                cardLetterOccurrences.add(letterNumberOfOccurences);
            }
            if (letterNumberOfOccurences == 5) {
                // "Five of a kind"
                cardType = 7;
                break;
            } else if (letterNumberOfOccurences == 4) {
                // "Four of a kind"
                cardType = 6;
                break;
            }
        }

        switch (cardLetterOccurrences.size()) {
            case 2:
                for (Integer letterOccurrences : cardLetterOccurrences) {
                    if (letterOccurrences == 3) {
                        // "full-house"
                        cardType = 5;
                        break;
                    }
                    if (letterOccurrences == 2) {
                        // "two-pair"
                        cardType = 3;
                    }
                }
                break;
            case 1:
                if (cardLetterOccurrences.get(0) == 2) {
                    // "one-pair"
                    cardType = 2;
                }
                if (cardLetterOccurrences.get(0) == 3) {
                    // "three-of-a-kind"
                    cardType = 4;
                }
                break;
            case 0:
                // "high-card"
                cardType = 1;
                break;
        }

        return cardType;
    }

    private int convertLetterToNumber(char letter) {
        if (Character.isDigit(letter) && Character.isDigit(letter)) {
            return (int)letter - '0';
        }
        int convertedNum = 0;
        switch (letter) {
            case 'T':
                convertedNum = 10;
                break;
            case 'J':
                convertedNum = 11;
                break;
            case 'Q':
                convertedNum = 12;
                break;
            case 'K':
                convertedNum = 13;
                break;
            case 'A':
                convertedNum = 14;
                break;
        }
        return convertedNum;
    }

    private String getStrongestCard(String firstCard, String secondCard) {
        int firstCardType = cardTypes.get(cardsList.indexOf(firstCard));
        int secondCardType = cardTypes.get(cardsList.indexOf(secondCard));

        String strongestCard = "";
        if (firstCardType == secondCardType) {
            for (int i = 0; i < firstCard.length(); i++) {
                char firstCardLetter = firstCard.charAt(i);
                char secondCardLetter = secondCard.charAt(i);
                if (convertLetterToNumber(firstCardLetter) > convertLetterToNumber(secondCardLetter)) {
                    strongestCard = firstCard;
                    break;
                } else if (convertLetterToNumber(firstCardLetter) < convertLetterToNumber(secondCardLetter)) {
                    strongestCard = secondCard;
                    break;
                }
            }
        } 
        else if (firstCardType > secondCardType) { strongestCard = firstCard; }
        else { strongestCard = secondCard; }

        return strongestCard;
    }

    private int getCardRank(String card, int cardType) {        
        int cardRank = cardsList.size();
        for (String comparingCard : cardsList) {
            if (!card.equals(comparingCard) && !getStrongestCard(card, comparingCard).equals(card)) {
                cardRank--;
            }
        }        
        return cardRank;
    }

    public long getTotalWinnings() {
        long totalWinnings = 0;
        for (int i = 0; i < cardsList.size(); i++) {
            long cardRank = getCardRank(cardsList.get(i), cardTypes.get(i));
            long cardBid = cardsBid.get(i);
            totalWinnings += cardBid * cardRank;
        }
        return totalWinnings;
    }
}