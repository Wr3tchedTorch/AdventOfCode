import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static String getTextFromFile(String filePath) {
        File targetFile = new File(filePath);
        String text = "";
        try {
            Scanner readFile = new Scanner(targetFile);
            while (readFile.hasNextLine()) {
                text += readFile.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String[] getLinesFromText(String text) {
        ArrayList<String> fileLines = new ArrayList<String>();        
        String line = "";
        for (char letter : text.toCharArray()) {
            if (letter == '\n') {
                fileLines.add(line);
                line = "";
            } else line += letter;
        }
        return fileLines.toArray(new String[0]);
    }

    public static char[][] get2DCharArrayFromText(String text) {
        String[] textLines = getLinesFromText(text);        
        char[][] char2DArray = new char[textLines.length][textLines[0].length()];
        
        int rows = 0;
        int columns = 0;        
        for (String line : textLines) {
            for (char letter : line.toCharArray()) {
                char2DArray[rows][columns] = letter;
                columns++;
            }
            columns = 0;
            rows++;
        }
        return char2DArray;
    }
}
