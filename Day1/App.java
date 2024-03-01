// Day 1: Trebuchet?!

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        File calibrationDocument = new File("input.txt");

        try {
            Scanner readText = new Scanner(calibrationDocument);

            int total = 0;

            while (readText.hasNextLine()) {                
                String line = readText.nextLine();                

                char first_num = '-';
                char second_num = '-';

                for (int i = 0; i < line.length(); i++) {
                    char letter = line.toCharArray()[i];

                    if (Character.isDigit(letter)) {
                        if (first_num == '-') {
                            first_num = letter;
                        } else second_num = letter;
                    }

                }

                if (second_num == '-') { second_num = first_num; }

                total += Integer.parseInt(Character.toString(first_num) + Character.toString(second_num));
            }
            System.out.println("Total: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}