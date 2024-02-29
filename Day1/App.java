/*  

Day 1 - Objetivo do exercicio: encontrar o primeiro e o último número em cada linha do texto, concatenar esses dois números, somar todos os números encontrados e exibir a soma no final.

Exemplo:
    1abc2
    pqr3stu8vwx
    a1b2c3d4e5f
    treb7uchet

Total = 12 + 38 + 115 + 77 = 142
*/



import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");

        try {
            Scanner readFile = new Scanner(inputFile);

            int total = 0;

            while (readFile.hasNextLine()) {                
                String line = readFile.nextLine();                

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