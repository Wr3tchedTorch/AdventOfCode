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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App app = new App();

        File inputTextFile = new File("input.txt");

        Schematic engineSchematic = new Schematic(app.getTextFromFile(inputTextFile));

        System.out.println(engineSchematic.getPartNumbers());
    }

    public String getTextFromFile(File file) {
        String fullText = "";

        try {
            Scanner readTextInput = new Scanner(file);


            while (readTextInput.hasNextLine()) {
                fullText += readTextInput.nextLine() + "\n";
            }

            readTextInput.close();

        } catch (FileNotFoundException e)  { e.printStackTrace(); }

        return fullText;    
    }
}
