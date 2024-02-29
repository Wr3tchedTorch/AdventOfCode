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
            Scanner readTextFile = new Scanner(inputFile);

            int total = 0;

            while (readTextFile.hasNextLine()) {
                String currentLine = readTextFile.nextLine();                

                char firstNum = '-';
                char secondNum = '-';

                for (int i = 0; i < currentLine.length(); i++) {
                    char letterBeingChecked = currentLine.toCharArray()[i];

                    if (Character.isDigit(letterBeingChecked)) {
                        boolean firstNumAlreadyFound = firstNum != '-';

                        if (firstNumAlreadyFound) {
                            secondNum = letterBeingChecked; 

                        } else firstNum = letterBeingChecked;
                    }

                }

                if (secondNum == '-') { secondNum = firstNum; }

                total += Integer.parseInt(Character.toString(firstNum) + Character.toString(secondNum));
            }

            readTextFile.close();

            System.out.println("Total: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}