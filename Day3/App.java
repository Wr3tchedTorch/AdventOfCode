import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static boolean isSpecialDigit(char digit) {
        return !(Character.isDigit(digit) || Character.isLetter(digit) || Character.isWhitespace(digit)
                || digit == '.');
    }

    public static void main(String[] args) {
        File puzzle_input = new File("input.txt");

        try {
            Scanner readInput = new Scanner(puzzle_input);

            ArrayList<String> lines = new ArrayList<>();

            int total = 0;

            while (readInput.hasNextLine()) {
                lines.add(readInput.nextLine());
            }

            for (int i = 0; i < lines.size(); i++) {
                String mid_line = lines.get(i);
                String bottom_line = "";
                String top_line = "";
                if (i - 1 >= 0)
                    top_line = lines.get(i - 1);
                if (i + 1 <= lines.size())
                    bottom_line = lines.get(i + 1);

                String[] numbers_on_mid_line = mid_line.replaceAll("[^0-9]", " ").replaceAll(" +", " ").trim()
                        .split(" ");

                for (String number : numbers_on_mid_line) {
                    int number_pos = mid_line.indexOf(number);

                    boolean hasVerticalNumber = false;

                    for (int j = 0; j < number.length(); j++) {
                        if (bottom_line != "") {
                            if (isSpecialDigit(bottom_line.toCharArray()[number_pos + j])) {
                                hasVerticalNumber = true;
                            }
                        }

                        if (top_line != "") {
                            if (isSpecialDigit(top_line.toCharArray()[number_pos + j])) {
                                hasVerticalNumber = true;
                            }
                        }
                    }

                    boolean checkBehind = false;
                    boolean checkInfront = false;

                    if (number_pos-1 >= 0) {
                        checkBehind = isSpecialDigit(mid_line.toCharArray()[number_pos - 1]);
                    }

                    if (number_pos+1 <= mid_line.length()) {
                        checkInfront = isSpecialDigit(mid_line.toCharArray()[number_pos + number.length() + 1]);
                    }

                    if (hasVerticalNumber ||
                            checkBehind ||
                            checkInfront) {
                        total += Integer.parseInt(number);
                        System.out.println("O número " + number + " é um part number!");
                    }
                }
            }

            System.out.println("Resultado total: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
